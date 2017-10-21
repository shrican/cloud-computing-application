/**
 * Varsha Bhanushali, 001234580, bhanushali.v@husky.neu.edu
 * Shrikant Mudholkar, 001284732, mudholkar.s@husky.neu.edu
 * Rahul Chandra, 01225683, chandra.ra@husky.neu.edu
 * Manish Patil, 001228956, patil.man@husky.neu.edu
 **/

package edu.neu.csye.tasks.endpoint;

import edu.neu.csye.tasks.endpoint.model.Task;
import edu.neu.csye.tasks.service.TasksService;
import edu.neu.csye.tasks.service.model.TaskDto;
import edu.neu.csye.tasks.service.model.TasksMapper;
import edu.neu.csye.useraccount.dataaccess.dao.UserAccountRepository;
import edu.neu.csye.useraccount.dataaccess.model.UserAccountEntity;
import edu.neu.csye.useraccount.service.UserAccountService;
import edu.neu.csye.useraccount.service.model.UserAccountDto;
import edu.neu.csye.useraccount.service.model.UserAccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

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


    @Override
    public Task create(Task task) {
        UserAccountDto userAccountDto = getUser();

        TaskDto taskDto = mapper.taskToDto(task);

        userAccountDto.getTaskDtoSet().add(taskDto);

        UserAccountEntity userAccountEntity = userAccountMapper.dtoToEntity(userAccountDto);
        userAccountEntity.getTaskEntity().add(mapper.dtoToEntity(taskDto));
        userAccountEntity = userAccountRepository.save(userAccountEntity);

        return mapper.dtoToTask(taskDto);

    }

//    @Override
//    public List<Task> get() {
//
//        service.getAll()
//
//        return null;
//    }

    public UserAccountDto getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userAccountService.getUserByUsername(username);
    }
}
