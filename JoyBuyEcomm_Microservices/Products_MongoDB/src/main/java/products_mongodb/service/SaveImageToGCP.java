package products_mongodb.service;

import products_mongodb.exception.BadRequestException;
import products_mongodb.exception.GCPFileUploadException;
import products_mongodb.util.DataBucketUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

@Service
public class SaveImageToGCP
{
    @Autowired
    private DataBucketUtil dataBucketUtil;

    public String uploadImage(String imageUrl, String originalFileName) throws IOException
    {
        if(originalFileName == null)
        {
            throw new BadRequestException("Original file name is null");
        }
        Path path = new File(originalFileName).toPath();

        try
        {
            File file = downloadFile(imageUrl);
            String contentType = "image/jpeg"; //Files.probeContentType(path);
            //DataBucketUtil dataBucketUtilBean = SpringContext.getBean(DataBucketUtil.class);
            return dataBucketUtil.uploadFile(file, originalFileName, contentType);
        }
        catch (Exception e)
        {
            throw new GCPFileUploadException(e.getMessage());
        }
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
