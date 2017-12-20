/**
 * Varsha Bhanushali, 001234580,
 * Shrikant Mudholkar, 001284732,
 * Rahul Chandra, 01225683,
 * Manish Patil, 001228956,
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
