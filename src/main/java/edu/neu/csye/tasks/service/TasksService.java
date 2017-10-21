/**
 * Varsha Bhanushali, 001234580, bhanushali.v@husky.neu.edu
 * Shrikant Mudholkar, 001284732, mudholkar.s@husky.neu.edu
 * Rahul Chandra, 01225683, chandra.ra@husky.neu.edu
 * Manish Patil, 001228956, patil.man@husky.neu.edu
 **/

package edu.neu.csye.tasks.service;

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


/**
 * A common place to store the business logic related to accessing tasks.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class TasksService {

    private final String FOLDER_PATH = "";

    @Autowired
    private final TasksDao tasksDao;

    @Autowired
    private final TasksMapper tasksMapper;

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

   // public AttachmentDto save(AttachmentDto attachmentDto) {return tasksDao.save(attachmentDto)}

    public String saveUploadedFile(String id, InputStream fileInputStream, FormDataContentDisposition cd) {
        OutputStream outpuStream = null;
        String fileName = cd.getFileName();
        System.out.println("File Name: " + cd.getFileName());
        String filePath = FOLDER_PATH + fileName;

        try {
            int read = 0;
            byte[] bytes = new byte[1024];
            outpuStream = new FileOutputStream(new File(filePath));
            while ((read = fileInputStream.read(bytes)) != -1) {
                outpuStream.write(bytes, 0, read);
            }
            outpuStream.flush();
            outpuStream.close();
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
