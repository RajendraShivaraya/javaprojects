package products_mongodb.util;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import products_mongodb.exception.GCPFileUploadException;
import products_mongodb.exception.InvalidFileTypeException;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GCPDataBucketUtil
{
    @Value("${gcp.config.file}")
    private String gcpConfigFile;

    @Value("${gcp.project.id}")
    private String gcpProjectId;

    @Value("${gcp.bucket.id}")
    private String gcpBucketId;

    @Value("${gcp.dir.name}")
    private String gcpDirectoryName;


    public String uploadFile(File file, String fileName, String contentType)
    {
        try
        {
            byte[] fileData = FileUtils.readFileToByteArray(file);
            InputStream inputStream = new ClassPathResource(gcpConfigFile).getInputStream();

            StorageOptions options = StorageOptions.newBuilder().setProjectId(gcpProjectId)
                    .setCredentials(GoogleCredentials.fromStream(inputStream)).build();

            Storage storage = options.getService();
            Bucket bucket = storage.get(gcpBucketId,Storage.BucketGetOption.fields());

            Blob blob = bucket.create(gcpDirectoryName + "/" + fileName + ".jpg", fileData, contentType);

            if(blob != null)
            {
                return "File " + blob.getName() + "Uploaded successfully in GCP, here is the URL : " + blob.getMediaLink();
            }
        }
        catch (Exception e)
        {
            throw new GCPFileUploadException("An error occurred while storing data to GCP");
        }
        throw new GCPFileUploadException("An error occurred while storing data to GCP");
    }

    private String checkFileExtension(String fileName)
    {
        if(fileName != null && fileName.contains("."))
        {
            String[] extensionList = {".png", ".jpeg", ".pdf", ".doc", ".mp3"};

            for(String extension: extensionList)
            {
                if (fileName.endsWith(extension))
                {
                    return extension;
                }
            }
        }
        throw new InvalidFileTypeException("Not a permitted file type");
    }

    public File downloadFile(String fileUrl) throws IOException
    {
        URL url = new URL(fileUrl);
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();
        File tempFile = File.createTempFile("temp", null); // Create a temporary file to store the downloaded content
        try (FileOutputStream fileOutputStream = new FileOutputStream(tempFile)) {
            StreamUtils.copy(inputStream, fileOutputStream);
        }
        inputStream.close();
        return tempFile; // Wrap the temporary file in a custom implementation of MultipartFile
    }
}
