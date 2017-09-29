package edu.neu.csye.useraccount.endpoint;

import edu.neu.csye.useraccount.endpoint.model.UserAccount;
import edu.neu.csye.useraccount.service.UserAccountService;
import edu.neu.csye.useraccount.service.model.UserAccountDto;
import edu.neu.csye.useraccount.service.model.UserAccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAccountEndointController implements UserAccountEndpointRest {

    private final UserAccountService userAccountService;
    private final UserAccountMapper userAccountMapper;

    @Override
    public UserAccountDto register(UserAccount userAccount)
    {
        return userAccountService.register(userAccountMapper.userAccountToDto(userAccount));
    }
}
