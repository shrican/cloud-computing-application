/**
 * Varsha Bhanushali, 001234580, bhanushali.v@husky.neu.edu
 * Shrikant Mudholkar, 001284732, mudholkar.s@husky.neu.edu
 * Rahul Chandra, 01225683, chandra.ra@husky.neu.edu
 * Manish Patil, 001228956, patil.man@husky.neu.edu
 **/

package edu.neu.csye.tasks.dataaccess;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import edu.neu.csye.tasks.dataaccess.model.TaskEntity;

public interface TasksRepository extends CrudRepository<TaskEntity, String> {

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    TaskEntity save(TaskEntity entity);

    @Transactional(propagation = Propagation.MANDATORY)
    TaskEntity findByTaskId(String taskId);

    @Override
    Iterable<TaskEntity> findAll();
}
