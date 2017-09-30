package edu.neu.csye.useraccount.dataaccess.dao;

import edu.neu.csye.useraccount.dataaccess.model.UserAccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface UserAccountRepository extends CrudRepository<UserAccountEntity,Integer> {

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    UserAccountEntity save(UserAccountEntity entity);

    @Transactional(propagation = Propagation.MANDATORY)
    UserAccountEntity findByEmail(String email);

    @Transactional(propagation = Propagation.MANDATORY)
    UserAccountEntity findByUsername(String username);
}
