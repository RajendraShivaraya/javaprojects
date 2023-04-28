package corepackage.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import java.io.File;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.stereotype.Service;

@Service
public class SaveFilesToAWS
{
    String accessKey = "test";
    String secretKey = "test";
    String bucketName = "americaregion";
    String excelFolderPath = "./excelfiles";
    String zipFolderPath = "./zipfiles";
    String folderName = "database/";
    String  serviceEndPoint = "s3.amazonaws.com";
    String region = "us-west-1";

    public String uploadExcelFiles()
    {
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        AmazonS3 s3client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(serviceEndPoint, region))
                .build();
        try
        {
            File folder = new File(excelFolderPath);
            File[] listOfFiles = folder.listFiles();
            long startTime = System.currentTimeMillis();
            for (File file : listOfFiles)
            {
                if (file.isFile())
                {
                    String keyName = folderName + file.getName() + Long.toString(startTime);
                    PutObjectRequest request = new PutObjectRequest(bucketName, keyName, file);
                    s3client.putObject(request);
                    System.out.println("File " + file.getName() + " uploaded to S3 successfully!");
                }
            }
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            double executionTimeInSeconds = executionTime / 1000.0;

            return "<h1>Excel file upload completed in " + executionTimeInSeconds + " seconds <h1>" ;
        }
        catch (AmazonServiceException e)
        {
            System.err.println(e.getErrorMessage());
        }

        return "<h1>Excel file upload - failed<h1>";
    }

    public String uploadZipFiles()
    {
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(serviceEndPoint, region))
                .build();
        try
        {
            File folder = new File(zipFolderPath);
            File[] listOfFiles = folder.listFiles();
            long startTime = System.currentTimeMillis();
            for (File file : listOfFiles)
            {
                if (file.isFile())
                {
                    String keyName = folderName + file.getName() + Long.toString(startTime);
                    PutObjectRequest request = new PutObjectRequest(bucketName, keyName, file);
                    s3client.putObject(request);
                    System.out.println("File " + file.getName() + " uploaded to S3 successfully!");
                }
            }
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;
            double executionTimeInSeconds = executionTime / 1000.0;
            return "<h1>Zip file upload completed in " + executionTimeInSeconds + " seconds <h1>";
        }
        catch (AmazonServiceException e)
        {
            System.err.println(e.getErrorMessage());
        }
        return "<h1>Zip file upload - failed<h1>";
    }
}
