package edu.neu.csye.useraccount.service;

import edu.neu.csye.useraccount.dataaccess.dao.UserAccountDao;
import edu.neu.csye.useraccount.service.model.UserAccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


/**
 * A common place to store the business logic related to accessing user account data.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UserAccountService {

    private final UserAccountDao userAccountDao;

    /**
     * Registers a user.
     *
     * @param userAccountDto the data about the user account
     * @return the userAccount that was updated
     */
    public UserAccountDto register(UserAccountDto userAccountDto){
        return userAccountDao.save(userAccountDto);
    }

}
