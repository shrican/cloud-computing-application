package edu.neu.csye.useraccount.endpoint;

/**
 * Varsha Bhanushali, 001234580, bhanushali.v@husky.neu.edu
 * Shrikant Mudholkar, 001284732, mudholkar.s@husky.neu.edu
 * Rahul Chandra, 01225683, chandra.ra@husky.neu.edu
 * Manish Patil, 001228956, patil.man@husky.neu.edu
 **/

import com.google.gson.JsonObject;
import edu.neu.csye.tasks.dataaccess.model.ResetTokenEntity;
import edu.neu.csye.useraccount.dataaccess.dao.UserAccountRepository;
import edu.neu.csye.useraccount.dataaccess.model.UserAccountEntity;
import edu.neu.csye.useraccount.endpoint.model.PasswordResetToken;
import edu.neu.csye.useraccount.service.UserAccountService;
import edu.neu.csye.useraccount.service.model.UserAccountDto;
import edu.neu.csye.useraccount.service.model.UserAccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;

@Component
@RequiredArgsConstructor
public class PasswordResetEndpointController implements PasswordResetEndpointRest {
    private final UserAccountService userAccountService;
    @Autowired
    private UserAccountRepository userAccountRepository;
    private final UserAccountMapper userAccountMapper;

    @Override
    public Response sendResetToken(ResetTokenEntity resetToken) {

        UserAccountDto userAccountDto = getUser();
        UserAccountEntity userAccountEntity = userAccountRepository.findByUsername(userAccountDto.getUsername());

        userAccountEntity.setResetEntity(resetToken);

        //logic to get uuid and use as token


        return Response.status(Response.Status.CREATED).build();
    }

    public UserAccountDto getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userAccountService.getUserByUsername(username);
    }

}