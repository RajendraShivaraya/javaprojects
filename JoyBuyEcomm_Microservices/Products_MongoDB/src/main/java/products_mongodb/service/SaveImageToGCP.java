package products_mongodb.service;

import products_mongodb.exception.BadRequestException;
import products_mongodb.exception.GCPFileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import products_mongodb.util.GCPDataBucketUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@Service
public class SaveImageToGCP
{
    @Autowired
    private GCPDataBucketUtil dataBucketUtil;

    public String uploadImage(String imageUrl, String originalFileName) throws IOException
    {
        if(originalFileName == null)
        {
            throw new BadRequestException("Original file name is null");
        }
        Path path = new File(originalFileName).toPath();

        try
        {
            File file = dataBucketUtil.downloadFile(imageUrl);
            String contentType = "image/jpeg"; //Files.probeContentType(path);
            //DataBucketUtil dataBucketUtilBean = SpringContext.getBean(DataBucketUtil.class);
            return dataBucketUtil.uploadFile(file, originalFileName, contentType);
        }
        catch (Exception e)
        {
            throw new GCPFileUploadException(e.getMessage());
        }
    }
}
