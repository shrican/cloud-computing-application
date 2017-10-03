package edu.neu.csye.useraccount.endpoint;

import edu.neu.csye.useraccount.endpoint.model.UserAccount;
import edu.neu.csye.useraccount.service.model.UserAccountDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user")
public interface UserAccountEndpointRest {

    @Path("/register")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    String register(UserAccount userAccount);
}
