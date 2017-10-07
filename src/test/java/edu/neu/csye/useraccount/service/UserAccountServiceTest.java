package edu.neu.csye.useraccount.service;

import edu.neu.csye.useraccount.dataaccess.dao.UserAccountDao;
import edu.neu.csye.useraccount.dataaccess.dao.UserAccountRepository;
import edu.neu.csye.useraccount.service.model.UserAccountDto;
import edu.neu.csye.useraccount.service.model.UserAccountMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;


@RunWith(MockitoJUnitRunner.class)
public class UserAccountServiceTest {


    @Mock
    UserAccountRepository userRepository;

    @Mock
    UserAccountService userAccountService;

    @Mock
    UserAccountDao userAccountDao;

    @Mock
    UserAccountMapper userAccountMapper;


    @Before
    public void setUp() throws Exception {
        userAccountService = new UserAccountService(userAccountDao, userAccountMapper);
        when(userAccountDao.save(any(UserAccountDto.class))).then(returnsFirstArg());

    }

    @Test
    public void ensuireUsernameIsUnique() throws Exception {

        when(userAccountDao.existsByUsername(anyString())).thenReturn(true);
    }
}