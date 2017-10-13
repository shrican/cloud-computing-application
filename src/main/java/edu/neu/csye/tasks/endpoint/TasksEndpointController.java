/**
 * Varsha Bhanushali, 001234580, bhanushali.v@husky.neu.edu
 * Shrikant Mudholkar, 001284732, mudholkar.s@husky.neu.edu
 * Rahul Chandra, 01225683, chandra.ra@husky.neu.edu
 * Manish Patil, 001228956, patil.man@husky.neu.edu
 **/

package edu.neu.csye.tasks.endpoint;

import edu.neu.csye.tasks.endpoint.model.Task;
import edu.neu.csye.tasks.service.TasksService;
import edu.neu.csye.tasks.service.model.TasksMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TasksEndpointController implements TasksEndpointRest {


    private TasksMapper mapper;

    private TasksService service;

    @Override
    public Task create(Task task) {

            //return mapper.entityToDto(service.save(mapper.taskToDto(task)));

    return null;
    }

    @Override
    public List<Task> get() {
        return null;
    }
}
