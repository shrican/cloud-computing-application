/**
 * Varsha Bhanushali, 001234580, bhanushali.v@husky.neu.edu
 * Shrikant Mudholkar, 001284732, mudholkar.s@husky.neu.edu
 * Rahul Chandra, 01225683, chandra.ra@husky.neu.edu
 * Manish Patil, 001228956, patil.man@husky.neu.edu
 **/

package edu.neu.csye.tasks.endpoint;

import edu.neu.csye.tasks.endpoint.model.Task;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/tasks")
public interface TasksEndpointRest {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Task create(Task task);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    List<Task> get();

}
