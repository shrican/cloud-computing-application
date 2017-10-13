package edu.neu.csye.useraccount.endpoint;

import com.google.gson.JsonObject;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *
 */
@Component
public class HomeEndpointController implements HomeEndpointRest {

    @Override
    public String welcome() {

        JsonObject jsonObject = new JsonObject();

        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) {
            jsonObject.addProperty("message", "you are not logged in!!!");
        } else {
            jsonObject.addProperty("message", "you are logged in. current time is " + new Date().toString());
        }

        return jsonObject.toString();
    }
}
