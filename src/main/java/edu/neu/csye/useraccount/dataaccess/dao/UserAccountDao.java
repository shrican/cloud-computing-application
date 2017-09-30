package edu.neu.csye.useraccount.dataaccess.dao;

import edu.neu.csye.useraccount.dataaccess.model.UserAccountEntity;
import edu.neu.csye.useraccount.service.model.UserAccountDto;
import edu.neu.csye.useraccount.service.model.UserAccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * A data access object that is responsible for accessing the data layer and retrieving user account data.
 */
@Component
@RequiredArgsConstructor
public class UserAccountDao {

    private final UserAccountRepository userAccountRepository;

    @Autowired
    private final UserAccountMapper userAccountMapper;

    /**
     * Saves the user account to the database.
     *
     * @param userAccountDto the dto representation of the User Account
     * @return a UserAccountDto
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public UserAccountDto save(UserAccountDto userAccountDto) {
        UserAccountEntity userAccountEntity = userAccountMapper.dtoToEntity(userAccountDto);

        userAccountEntity = userAccountRepository.save(userAccountEntity);

        return userAccountMapper.entityToDto(userAccountEntity);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public UserAccountDto loadUserByUsername(String username) {
        UserAccountEntity user = userAccountRepository.findByUsername(username);
        if (user == null) {

        }
        return userAccountMapper.entityToDto(user);
    }
}
