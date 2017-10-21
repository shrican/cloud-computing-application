
/**
 * Varsha Bhanushali, 001234580, bhanushali.v@husky.neu.edu
 * Shrikant Mudholkar, 001284732, mudholkar.s@husky.neu.edu
 * Rahul Chandra, 01225683, chandra.ra@husky.neu.edu
 * Manish Patil, 001228956, patil.man@husky.neu.edu
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
