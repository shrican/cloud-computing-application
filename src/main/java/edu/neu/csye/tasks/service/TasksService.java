/**
 * Varsha Bhanushali, 001234580, bhanushali.v@husky.neu.edu
 * Shrikant Mudholkar, 001284732, mudholkar.s@husky.neu.edu
 * Rahul Chandra, 01225683, chandra.ra@husky.neu.edu
 * Manish Patil, 001228956, patil.man@husky.neu.edu
 **/

package edu.neu.csye.tasks.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import edu.neu.csye.tasks.dataaccess.TasksDao;
import edu.neu.csye.tasks.endpoint.model.Task;
import edu.neu.csye.tasks.service.model.TaskDto;
import edu.neu.csye.tasks.service.model.TasksMapper;
import lombok.RequiredArgsConstructor;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.*;
import java.sql.Timestamp;


/**
 * A common place to store the business logic related to accessing tasks.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class TasksService {

    private static String bucketName = System.getProperty("bucketName");
    private static Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    private final String FOLDER_PATH = "";
    @Autowired
    private final TasksDao tasksDao;
    @Autowired
    private final TasksMapper tasksMapper;
    //private static String keyName = "File" + timestamp.toString();

    public String uploadToS3(String filepath) throws IOException {

        AmazonS3 s3client = AmazonS3ClientBuilder.standard()
                .withCredentials(new InstanceProfileCredentialsProvider(false))
                .build();
        try {
            System.out.println("Uploading a new object to S3 from a file\n");
            File file = new File(filepath);

            String bucketName = System.getProperty("bucketName");
            s3client.putObject(new PutObjectRequest(
                    bucketName, filepath + timestamp, file));


        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
                    "means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
                    "means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }

        return "https://s3.amazonaws.com/"+bucketName+"/"+filepath + timestamp;
    }

    public void deletefroms3(String keyname) {
        AmazonS3 s3client = new AmazonS3Client(new ProfileCredentialsProvider());
        try {
            s3client.deleteObject(new DeleteObjectRequest(bucketName, keyname));
        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException.");
            System.out.println("Error Message: " + ace.getMessage());
        }

    }

    /**
     * Registers a user.
     *
     * @param taskDto the data about the user account
     * @return the userAccount that was updated
     */
    public TaskDto save(TaskDto taskDto) {
        return tasksDao.save(taskDto);
    }

    public Task loadTaskById(String id) {

        TaskDto task = tasksDao.loadTaskById(id);

        return tasksMapper.dtoToTask(task);
    }


    public String saveUploadedFile(String id, InputStream fileInputStream, FormDataContentDisposition cd) {
        OutputStream outpuStream = null;
        String fileName = cd.getFileName();
        System.out.println("File Name: " + cd.getFileName());
        String filePath="";


        try {
            int read = 0;
            byte[] bytes = new byte[1024];
            outpuStream = new FileOutputStream(new File(filePath));
            while ((read = fileInputStream.read(bytes)) != -1) {
                outpuStream.write(bytes, 0, read);
            }
            outpuStream.flush();
            outpuStream.close();
            filePath= uploadToS3(cd.getFileName());
        } catch (IOException iox) {
            iox.printStackTrace();
        } finally {
            if (outpuStream != null) {
                try {
                    outpuStream.close();
                } catch (Exception ex) {
                }
            }

        }
        return filePath;
    }

}
