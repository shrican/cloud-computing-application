
/**
 * Varsha Bhanushali, 001234580, bhanushali.v@husky.neu.edu
 * Shrikant Mudholkar, 001284732, mudholkar.s@husky.neu.edu
 * Rahul Chandra, 01225683, chandra.ra@husky.neu.edu
 * Manish Patil, 001228956, patil.man@husky.neu.edu
 **/
package edu.neu.csye.useraccount.service;

import edu.neu.csye.useraccount.dataaccess.dao.UserAccountDao;
import edu.neu.csye.useraccount.service.model.UserAccountDto;
import edu.neu.csye.useraccount.service.model.UserAccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;


/**
 * A common place to store the business logic related to accessing user account data.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UserAccountService implements UserDetailsService {

    @Autowired
    private final UserAccountDao userAccountDao;

    @Autowired
    private final UserAccountMapper userAccountMapper;

    /**
     * Registers a user.
     *
     * @param userAccountDto the data about the user account
     * @return the userAccount that was updated
     */
    public UserAccountDto register(UserAccountDto userAccountDto) {
        return userAccountDao.save(userAccountDto);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        UserAccountDto userRecord = userAccountDao.loadUserByUsername(username);

        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("User");

        UserDetails springUserRecord = new User(userRecord.getUsername(), userRecord.getPassword(), Arrays.asList(grantedAuthority));

        return springUserRecord;
    }

    public boolean ensuireUsernameIsUnique(String username) {
        return !userAccountDao.existsByUsername(username);
    }
}
