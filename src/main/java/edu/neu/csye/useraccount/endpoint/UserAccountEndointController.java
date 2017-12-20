package edu.neu.csye.useraccount.endpoint;

/**
 * Varsha Bhanushali, 001234580,
 * Shrikant Mudholkar, 001284732,
 * Rahul Chandra, 01225683,
 * Manish Patil, 001228956,
 **/

import com.google.gson.JsonObject;
import edu.neu.csye.useraccount.endpoint.model.UserAccount;
import edu.neu.csye.useraccount.service.UserAccountService;
import edu.neu.csye.useraccount.service.model.UserAccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAccountEndointController implements UserAccountEndpointRest {

    private final UserAccountService userAccountService;
    private final UserAccountMapper userAccountMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public String register(UserAccount userAccount) {
        JsonObject jsonObject = new JsonObject();
        userAccount.setPassword(BCrypt.hashpw(userAccount.getPassword(), BCrypt.gensalt()));
        if (userAccountService.ensuireUsernameIsUnique(userAccount.getUsername())) {
            userAccountService.save(userAccountMapper.userAccountToDto(userAccount));
            jsonObject.addProperty("message", "Registration Successful");

        } else {
            jsonObject.addProperty("message", "Username exixst");
        }

        return jsonObject.toString();
    }
}