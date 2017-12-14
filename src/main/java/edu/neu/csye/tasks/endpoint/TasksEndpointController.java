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
import edu.neu.csye.tasks.endpoint.model.Attachment;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
    public Task create(TaskEntity task) {

        UserAccountDto userAccountDto = getUser();
        UserAccountEntity userAccountEntity = userAccountRepository.findByUsername(userAccountDto.getUsername());

        userAccountEntity.getTaskEntity().add(task);
        userAccountEntity = userAccountRepository.save(userAccountEntity);

        TaskEntity updatedTask = userAccountEntity.getTaskEntity().stream().filter(filteredTask -> filteredTask.getDescription().equals(task.getDescription())).findFirst().orElse(task);

        return mapper.entityToTasks(updatedTask);
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
        if (!isUserAuthorized(taskId)) {
            sendViaException();
        }
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

        if (userAccountEntity.getTaskEntity().contains(taskEntity)) {
            userAccountEntity.getTaskEntity().remove(taskEntity);

            userAccountRepository.save(userAccountEntity);

            return Response.status(Response.Status.FORBIDDEN).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }

    }

    public Response deleteAttachment(String taskId, String idAttachments) {

        if ("".equals(taskId)) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        UserAccountDto userAccountDto = getUser();

        AttachmentEntity attachmentEntity = attachmentRepository.findByAttachmentId(idAttachments);
        service.deletefroms3(attachmentEntity.getUrl());
        TaskEntity taskEntity = tasksRepository.findByTaskId(taskId);

        taskEntity.getAttachment().remove(attachmentEntity);

        tasksRepository.save(taskEntity);


        return Response.status(Response.Status.OK).build();
    }


    public AttachmentEntity createAttachment(String id, InputStream fileInputStream,
                                             FormDataContentDisposition cd) {

        UserAccountDto userAccountDto = getUser();

        UserAccountEntity userAccountEntity = userAccountRepository.findByUsername(userAccountDto.getUsername());

        String fileurl = service.saveUploadedFile(id, fileInputStream, cd);

        TaskEntity task = tasksRepository.findByTaskId(id);
        TaskDto taskDto = mapper.entityToDto(task);

        AttachmentDto att = new AttachmentDto();
        att.setUrl(fileurl);

        task.getAttachment().add(mapper.attachmentDtoToEntity(att));

        TaskEntity taskEntity = tasksRepository.save(task);

        AttachmentEntity updatedAtt = taskEntity.getAttachment().stream().filter(filteredAtt -> filteredAtt.getUrl().equals(fileurl)).findFirst().orElse(mapper.attachmentDtoToEntity(att));

        return updatedAtt;

    }

    @Override
    public Set<Attachment> getAttachments(String taskId) {
        //UserAccountDto userAccountDto = getUser();

        TaskEntity taskEntity = tasksRepository.findByTaskId(taskId);
        System.out.println(taskEntity);

        return mapper.setEntitiestoAttSet(taskEntity.getAttachment());
    }

    public boolean isUserAuthorized(String taskId) {

        UserAccountEntity userAccountEntity = userAccountRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        if (userAccountEntity.getTaskEntity().stream().filter(task -> task.getTaskId().equals(taskId)).findFirst().orElse(null) != null) {
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/exception", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity sendViaException() {
        throw new ForbiddenException();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    class ForbiddenException extends RuntimeException {
    }
}