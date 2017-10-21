
/**
 * Varsha Bhanushali, 001234580, bhanushali.v@husky.neu.edu
 * Shrikant Mudholkar, 001284732, mudholkar.s@husky.neu.edu
 * Rahul Chandra, 01225683, chandra.ra@husky.neu.edu
 * Manish Patil, 001228956, patil.man@husky.neu.edu
 **/
package edu.neu.csye.useraccount.dataaccess.dao;

import edu.neu.csye.useraccount.dataaccess.model.UserAccountEntity;
import edu.neu.csye.useraccount.service.model.UserAccountDto;
import edu.neu.csye.useraccount.service.model.UserAccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
    @Transactional()
    public UserAccountDto save(UserAccountDto userAccountDto) {
        UserAccountEntity userAccountEntity = userAccountMapper.dtoToEntity(userAccountDto);

        userAccountEntity = userAccountRepository.save(userAccountEntity);

        return userAccountMapper.entityToDto(userAccountEntity);
    }

    @Transactional()
    public UserAccountDto loadUserByUsername(String username) {
        UserAccountEntity user = userAccountRepository.findByUsername(username);
        if (user == null) {

        }
        return userAccountMapper.entityToDto(user);
    }

    public boolean existsByUsername(String username) {
        return userAccountRepository.findByUsername(username) != null;
    }
}
