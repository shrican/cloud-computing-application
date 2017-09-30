package edu.neu.csye.useraccount.endpoint;

import com.google.gson.JsonObject;
import edu.neu.csye.useraccount.endpoint.model.UserAccount;
import edu.neu.csye.useraccount.service.UserAccountService;
import edu.neu.csye.useraccount.service.model.UserAccountDto;
import edu.neu.csye.useraccount.service.model.UserAccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAccountEndointController implements UserAccountEndpointRest {

    private final UserAccountService userAccountService;
    private final UserAccountMapper userAccountMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public String register(UserAccount userAccount)
    {
        JsonObject jsonObject = new JsonObject();
        userAccount.setPassword(bCryptPasswordEncoder.encode(userAccount.getPassword()));
        if(userAccountService.ensuireUsernameIsUnique(userAccount.getUsername())){
            userAccountService.register(userAccountMapper.userAccountToDto(userAccount));
            jsonObject.addProperty("message", "Registration Successful");

        }
        else {
            jsonObject.addProperty("message", "Username exixst");
        }

        return jsonObject.toString();
    }
}