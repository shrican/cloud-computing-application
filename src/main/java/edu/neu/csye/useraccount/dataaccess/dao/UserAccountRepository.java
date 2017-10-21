/**
 * Varsha Bhanushali, 001234580, bhanushali.v@husky.neu.edu
 * Shrikant Mudholkar, 001284732, mudholkar.s@husky.neu.edu
 * Rahul Chandra, 01225683, chandra.ra@husky.neu.edu
 * Manish Patil, 001228956, patil.man@husky.neu.edu
 **/
package edu.neu.csye.useraccount.dataaccess.dao;

import edu.neu.csye.useraccount.dataaccess.model.UserAccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserAccountRepository extends CrudRepository<UserAccountEntity, Integer> {

    @Override
    @Transactional()
    UserAccountEntity save(UserAccountEntity entity);

    @Transactional()
    UserAccountEntity findByUsername(String username);
}
