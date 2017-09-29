package edu.neu.csye.useraccount.service.model;

import edu.neu.csye.useraccount.dataaccess.model.UserAccountEntity;
import edu.neu.csye.useraccount.endpoint.model.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserAccountMapper {

    UserAccountDto entityToDto(UserAccountEntity userAccountEntity);

    UserAccountEntity dtoToEntity(UserAccountDto userAccountDto);

    UserAccountDto userAccountToDto(UserAccount userAccount);
}
