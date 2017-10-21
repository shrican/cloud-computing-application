/**
 * Varsha Bhanushali, 001234580, bhanushali.v@husky.neu.edu
 * Shrikant Mudholkar, 001284732, mudholkar.s@husky.neu.edu
 * Rahul Chandra, 01225683, chandra.ra@husky.neu.edu
 * Manish Patil, 001228956, patil.man@husky.neu.edu
 **/

package edu.neu.csye.tasks.endpoint;

import edu.neu.csye.tasks.endpoint.model.Task;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Path("/tasks")
public interface TasksEndpointRest {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Task create(Task task);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Set<Task> get();

    @Path("/{taskId}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Task update(@PathParam("taskId") String taskId, Task task);

    @Path("/{taskId}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    Response delete(@PathParam("taskId") String taskId);

//    @Path("/{taskId}/attachments")
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    List<Attachment> getAttachments(@PathParam("taskId") int taskId);
//
//    @Path("/{taskId}/attachments")
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    Attachment createAttachment (@PathParam("taskId") int taskId, Attachment attachments);
//

    @Path("/{taskId}/attachments/{idAttachments}")
    @DELETE
    Response deleteAttachment(@PathParam("taskId") String taskId, @PathParam("idAttachments") String idAttachments);

}
