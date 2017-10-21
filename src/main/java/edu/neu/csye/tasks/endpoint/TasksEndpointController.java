/**
 * Varsha Bhanushali, 001234580, bhanushali.v@husky.neu.edu
 * Shrikant Mudholkar, 001284732, mudholkar.s@husky.neu.edu
 * Rahul Chandra, 01225683, chandra.ra@husky.neu.edu
 * Manish Patil, 001228956, patil.man@husky.neu.edu
 **/

package edu.neu.csye.tasks.endpoint;

import edu.neu.csye.tasks.dataaccess.AttachmentRepository;
import edu.neu.csye.tasks.dataaccess.TasksRepository;
import edu.neu.csye.tasks.dataaccess.model.AttachmentEntity;
import edu.neu.csye.tasks.dataaccess.model.TaskEntity;
import edu.neu.csye.tasks.endpoint.model.Task;
import edu.neu.csye.tasks.service.TasksService;
import edu.neu.csye.tasks.service.model.AttachmentDto;
import edu.neu.csye.tasks.service.model.TaskDto;
import edu.neu.csye.tasks.service.model.TasksMapper;
import edu.neu.csye.useraccount.dataaccess.dao.UserAccountRepository;
import edu.neu.csye.useraccount.dataaccess.model.UserAccountEntity;
import edu.neu.csye.useraccount.service.UserAccountService;
import edu.neu.csye.useraccount.service.model.UserAccountDto;
import edu.neu.csye.useraccount.service.model.UserAccountMapper;
import lombok.RequiredArgsConstructor;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class TasksEndpointController implements TasksEndpointRest {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private TasksMapper mapper;

    @Autowired
    private TasksService service;

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Autowired
    private TasksRepository tasksRepository;

    @Autowired
    private AttachmentRepository attachmentRepository;


    @Override
    public Task create(Task task) {
        UserAccountDto userAccountDto = getUser();

        TaskDto taskDto = mapper.taskToDto(task);

        userAccountDto.getTaskDtoSet().add(taskDto);

        UserAccountEntity userAccountEntity = userAccountRepository.findByUsername(userAccountDto.getUsername());

        userAccountEntity.getTaskEntity().add(mapper.dtoToEntity(taskDto));
        userAccountEntity = userAccountRepository.save(userAccountEntity);

        return mapper.dtoToTask(taskDto);

    }

    @Override
    public Set<Task> get() {

        UserAccountDto userAccountDto = getUser();

        UserAccountEntity userAccountEntity = userAccountRepository.findByUsername(userAccountDto.getUsername());

        return mapper.setEntittiesToTask(userAccountEntity.getTaskEntity());

    }

    public UserAccountDto getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userAccountService.getUserByUsername(username);
    }


    public Task update(String taskId, Task task) {
        UserAccountDto userAccountDto = getUser();

        UserAccountEntity userAccountEntity = userAccountRepository.findByUsername(userAccountDto.getUsername());

        TaskEntity taskEntity = tasksRepository.findByTaskId(taskId);

        taskEntity.setDescription(task.getDescription());

        tasksRepository.save(taskEntity);

        return mapper.entityToTasks(taskEntity);
    }

    public Response delete(String taskId) {

        if ("".equals(taskId)) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        UserAccountDto userAccountDto = getUser();

        UserAccountEntity userAccountEntity = userAccountRepository.findByUsername(userAccountDto.getUsername());

        TaskEntity taskEntity = tasksRepository.findByTaskId(taskId);


        userAccountEntity.getTaskEntity().remove(taskEntity);

        userAccountRepository.save(userAccountEntity);

        return Response.status(Response.Status.OK).build();

    }

    public Response deleteAttachment(String taskId, String idAttachments) {

        if ("".equals(taskId)) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        UserAccountDto userAccountDto = getUser();

        UserAccountEntity userAccountEntity = userAccountRepository.findByUsername(userAccountDto.getUsername());


        AttachmentEntity attachmentEntity = attachmentRepository.findByAttachmentId(idAttachments);

        attachmentRepository.delete(attachmentEntity);

        return Response.status(Response.Status.OK).build();
    }


    public void createAttachment(String id, InputStream fileInputStream,
                                 FormDataContentDisposition cd) {

        UserAccountDto userAccountDto = getUser();

        UserAccountEntity userAccountEntity = userAccountRepository.findByUsername(userAccountDto.getUsername());


        String fileurl = service.saveUploadedFile(id, fileInputStream, cd);


        TaskEntity task = tasksRepository.findByTaskId(id);
        TaskDto taskDto = mapper.entityToDto(task);

        AttachmentDto att = new AttachmentDto();
        att.setUrl(fileurl);

        task.getAttachment().add(mapper.attachmentDtoToEntity(att));

        tasksRepository.save(task);


//        taskDto.getAttachmentDtos().add(att);
//
//        userAccountDto.getTaskDtoSet().add(mapper.entityToDto());
//
//        UserAccountEntity userAccountEntity = userAccountRepository.findByUsername(userAccountDto.getUsername());
//
//        userAccountEntity.getTaskEntity().add(mapper.dtoToEntity(taskDto));
//        userAccountEntity = userAccountRepository.save(userAccountEntity);

    }

}
