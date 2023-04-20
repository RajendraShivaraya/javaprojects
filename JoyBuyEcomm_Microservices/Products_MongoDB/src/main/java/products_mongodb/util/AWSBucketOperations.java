package products_mongodb.util;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.StreamUtils;
import products_mongodb.ProductsMongoDbApplication;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;

public class AWSBucketOperations
{
    private static final Logger logger = LogManager.getLogger(ProductsMongoDbApplication.class);
    public AmazonS3 s3client;
    public String bucketName;
    public String bucketDirectoryPath;

    public AWSBucketOperations(String accessKey,
                               String secretKey,
                               String s3BucketName,
                               String bucketDirectory,
                               Regions region)
    {
        AWSCredentials credentials = new BasicAWSCredentials( accessKey, secretKey);
        bucketName = s3BucketName;
        bucketDirectoryPath = bucketDirectory;
        s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                //.withRegion(Regions.US_WEST_2)
                .build();
    }


    public void listBuckets()
    {
        List<Bucket> buckets = s3client.listBuckets();
        for(Bucket bucket : buckets)
        {
            System.out.println(bucket.getName());
        }
    }
    public Bucket createBucket(String _bucketName)
    {
        if(s3client.doesBucketExistV2(_bucketName))
        {
            logger.error("Bucket " + _bucketName + " already exists");
            return new Bucket(_bucketName);
        }
        bucketName = _bucketName;
        return s3client.createBucket(bucketName);
    }

    public File downloadFileByURL(String fileUrl) throws IOException
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

    public InputStream downloadFileStreamByURL(String fileUrl) throws IOException
    {
        URL url = new URL(fileUrl);
        return url.openStream();
    }

    public String uploadFileInputStream(InputStream inputStream, String originalFileName)
    {
        try
        {
            byte[] fileBytes = IOUtils.toByteArray(inputStream);

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("image/jpeg");
            metadata.setContentDisposition("inline");
            metadata.setContentLength(fileBytes.length);

            String objectKey = bucketDirectoryPath + originalFileName;
            PutObjectResult result = s3client.putObject(bucketName, objectKey, new ByteArrayInputStream(fileBytes), metadata);

            String region = s3client.getBucketLocation(bucketName);
            String fileUrl = String.format("https://%s.s3-%s.amazonaws.com/%s", bucketName, region, objectKey);

            logger.info("File uploaded successfully. URL: {}", fileUrl);

            return fileUrl;
        } catch (Exception e) {
            logger.fatal(e.getMessage());
        }
        return "";
    }


    public void deleteObjects(String[] fileNamesWithType)
    {
        try {
            for (int i = 0; i < fileNamesWithType.length; i++) {
                fileNamesWithType[i] = bucketDirectoryPath + fileNamesWithType[i];
            }
            logger.info("Objects being deleted : " + Arrays.toString(fileNamesWithType));

            DeleteObjectsRequest delObjReq = new DeleteObjectsRequest(bucketName)
                    .withKeys(fileNamesWithType);
            DeleteObjectsResult result = s3client.deleteObjects(delObjReq);

            logger.info(Arrays.toString(result.getDeletedObjects().toArray()));
        }
        catch (Exception e)
        {
            logger.fatal(e.getMessage());
        }
    }

    public String uploadFile(File fileToBeUploaded)
    {
        try
        {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("image/jpeg");
            PutObjectResult result = s3client.putObject(bucketName, bucketDirectoryPath, fileToBeUploaded);
            logger.info("File uploaded successfully");
            logger.info(result.getMetadata());
            return "";
        }
        catch (Exception e)
        {
            logger.fatal(e.getMessage());
        }

        return "";
    }
}
