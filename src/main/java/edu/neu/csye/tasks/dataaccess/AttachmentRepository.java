/**
 * Varsha Bhanushali, 001234580,
 * Shrikant Mudholkar, 001284732,
 * Rahul Chandra, 01225683,
 * Manish Patil, 001228956,
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
