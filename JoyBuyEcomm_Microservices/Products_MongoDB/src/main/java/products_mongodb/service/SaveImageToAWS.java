package products_mongodb.service;

import com.amazonaws.regions.Regions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import products_mongodb.util.AWSBucketOperations;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Service
public class SaveImageToAWS
{
    public String uploadImage(String imageUrl, String originalFileName) throws IOException
    {
        AWSBucketOperations bucketOperations = new AWSBucketOperations(
                "AKIAVSGOY4HUYAFCGP5S",
                "7q9F5VBGLfUnBvcQFG3mSqL5Ulde3yQJMAtwGUv/",
                "joybuy-bucket-ecomm-products",
                "product_database/",
                Regions.US_EAST_2);

        InputStream inputStream = bucketOperations.downloadFileStreamByURL(imageUrl);
        return bucketOperations.uploadFileInputStream(inputStream, originalFileName);
    }
}
