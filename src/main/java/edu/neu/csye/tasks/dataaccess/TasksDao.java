/**
 * Varsha Bhanushali, 001234580, bhanushali.v@husky.neu.edu
 * Shrikant Mudholkar, 001284732, mudholkar.s@husky.neu.edu
 * Rahul Chandra, 01225683, chandra.ra@husky.neu.edu
 * Manish Patil, 001228956, patil.man@husky.neu.edu
 **/

package edu.neu.csye.tasks.dataaccess;

import edu.neu.csye.tasks.dataaccess.model.TaskEntity;
import edu.neu.csye.tasks.service.model.TaskDto;
import edu.neu.csye.tasks.service.model.TasksMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object that is responsible for accessing the data layer and retrieving tasks data.
 */
@Component
@RequiredArgsConstructor
public class TasksDao {

    private TasksMapper tasksMapper;
    private TasksRepository tasksRepository;

    /**
     * Saves the task to the database.
     *
     * @param taskDto the dto representation of the Task
     * @return a TaskDto
     */
    @Transactional()
    public TaskDto save(TaskDto taskDto) {
        TaskEntity taskEntity = tasksMapper.dtoToEntity(taskDto);

        taskEntity = tasksRepository.save(taskEntity);

        return tasksMapper.entityToDto(taskEntity);
    }

    @Transactional()
    public TaskDto loadTaskById(String id) {
        TaskEntity task = tasksRepository.findByTaskId(id);
        if (task == null) {

        }
        return tasksMapper.entityToDto(task);
    }


    public boolean existsById(String id) {
        return tasksRepository.findByTaskId(id) != null;
    }
}
