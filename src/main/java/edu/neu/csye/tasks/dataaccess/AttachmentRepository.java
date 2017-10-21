/**
 * Varsha Bhanushali, 001234580, bhanushali.v@husky.neu.edu
 * Shrikant Mudholkar, 001284732, mudholkar.s@husky.neu.edu
 * Rahul Chandra, 01225683, chandra.ra@husky.neu.edu
 * Manish Patil, 001228956, patil.man@husky.neu.edu
 **/

package edu.neu.csye.tasks.dataaccess;

import edu.neu.csye.tasks.dataaccess.model.AttachmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface AttachmentRepository extends CrudRepository<AttachmentEntity, String> {

    @Override
    @Transactional()
    AttachmentEntity save(AttachmentEntity entity);


    AttachmentEntity findByAttachmentId(String id);


    @Override
    Iterable<AttachmentEntity> findAll();

    @Override
    void delete(AttachmentEntity attachmentEntity);
}
