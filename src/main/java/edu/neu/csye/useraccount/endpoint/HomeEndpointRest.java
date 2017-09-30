package edu.neu.csye.useraccount.endpoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public interface HomeEndpointRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String welcome();
}