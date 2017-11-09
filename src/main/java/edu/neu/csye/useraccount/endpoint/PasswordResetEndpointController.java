package edu.neu.csye.useraccount.endpoint;

/**
 * Varsha Bhanushali, 001234580, bhanushali.v@husky.neu.edu
 * Shrikant Mudholkar, 001284732, mudholkar.s@husky.neu.edu
 * Rahul Chandra, 01225683, chandra.ra@husky.neu.edu
 * Manish Patil, 001228956, patil.man@husky.neu.edu
 **/

import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.iotdata.model.PublishRequest;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishResult;
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

                AmazonSNSClient snsClient = new AmazonSNSClient(new InstanceProfileCredentialsProvider());
                snsClient.setRegion(Region.getRegion(Regions.US_EAST_1));
                //get topic arn
                String topicArn= snsClient.createTopic("password_reset").getTopicArn();
                PublishRequest publishRequest = new PublishRequest(topicArn, userAccountEntity.getUsername());
                PublishResult publishResult = snsClient.publish(publishRequest);
                //print MessageId of message published to SNS topic
                System.out.println("Password reset message sent!");

            return Response.status(Response.Status.OK).build();

        }





        return Response.status(Response.Status.CREATED).build();
    }

    public UserAccountDto getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userAccountService.getUserByUsername(username);
    }

}