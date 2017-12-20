/**
 * Varsha Bhanushali, 001234580,
 * Shrikant Mudholkar, 001284732,
 * Rahul Chandra, 01225683,
 * Manish Patil, 001228956,
 **/

package edu.neu.csye.tasks.dataaccess;

import edu.neu.csye.tasks.dataaccess.model.TaskEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface TasksRepository extends CrudRepository<TaskEntity, String> {

    @Override
    @Transactional()
    TaskEntity save(TaskEntity entity);

    @Transactional()
    TaskEntity findByTaskId(String taskId);

    @Override
    Iterable<TaskEntity> findAll();

    @Override
    void delete(TaskEntity taskEntity);
}
