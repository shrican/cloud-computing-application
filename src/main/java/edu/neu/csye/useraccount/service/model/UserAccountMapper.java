
/**
 * Varsha Bhanushali, 001234580,
 * Shrikant Mudholkar, 001284732,
 * Rahul Chandra, 01225683,
 * Manish Patil, 001228956,
 **/
package edu.neu.csye.useraccount.service.model;

import edu.neu.csye.tasks.service.model.TasksMapper;
import edu.neu.csye.useraccount.dataaccess.model.UserAccountEntity;
import edu.neu.csye.useraccount.endpoint.model.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = TasksMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserAccountMapper {

    UserAccountDto entityToDto(UserAccountEntity userAccountEntity);

    UserAccountEntity dtoToEntity(UserAccountDto userAccountDto);

    UserAccountDto userAccountToDto(UserAccount userAccount);

}
