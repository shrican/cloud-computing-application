package edu.neu.csye.useraccount.endpoint;

import edu.neu.csye.useraccount.dataaccess.dao.UserAccountRepository;
import edu.neu.csye.useraccount.dataaccess.model.UserAccountEntity;
import edu.neu.csye.useraccount.service.model.UserAccountDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;

@DataJpaTest
@WebMvcTest(controllers = UserAccountEndointController.class, secure = false)
@RunWith(MockitoJUnitRunner.class)
public class UserAccountEndpointControllerTest {


    @Mock
    UserAccountRepository userRepository;

    @Autowired
    MockMvc mockMvc;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);

        UserAccountEntity createUser = new UserAccountEntity();
        createUser.setUsername("shri");
        createUser.setPassword("shri");

        when(userRepository.findByUsername("shri")).thenReturn(createUser);
    }

    @Test
    public void register() throws Exception {

        UserAccountEntity retrievedUser = userRepository.findByUsername("shri");
        Assert.assertEquals(retrievedUser.getUsername(),"shri");
    }

}
