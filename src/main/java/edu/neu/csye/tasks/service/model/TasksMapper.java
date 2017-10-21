/**
 * Varsha Bhanushali, 001234580, bhanushali.v@husky.neu.edu
 * Shrikant Mudholkar, 001284732, mudholkar.s@husky.neu.edu
 * Rahul Chandra, 01225683, chandra.ra@husky.neu.edu
 * Manish Patil, 001228956, patil.man@husky.neu.edu
 **/

package edu.neu.csye.tasks.service.model;

import edu.neu.csye.tasks.dataaccess.model.AttachmentEntity;
import edu.neu.csye.tasks.dataaccess.model.TaskEntity;
import edu.neu.csye.tasks.endpoint.model.Attachment;
import edu.neu.csye.tasks.endpoint.model.Task;
import edu.neu.csye.useraccount.service.model.UserAccountMapper;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Mapper(componentModel = "spring", uses = UserAccountMapper.class,unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface TasksMapper {

    TaskEntity dtoToEntity(TaskDto taskDto);

    Task dtoToTask(TaskDto taskDto);

    TaskDto entityToDto(TaskEntity taskEntity);

    AttachmentDto attachmentEntityToDto(AttachmentEntity attachmentEntity);

    AttachmentEntity attachmentDtoToEntity(AttachmentDto attachmentDto);

    Attachment dtoToAttachment(AttachmentDto attachmentDto);

    TaskDto taskToDto(Task task);

    Set<TaskEntity> toTaskSetEntity(Set<TaskDto> taskDtos);

}
