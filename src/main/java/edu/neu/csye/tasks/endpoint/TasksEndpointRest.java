/**
 * Varsha Bhanushali, 001234580, bhanushali.v@husky.neu.edu
 * Shrikant Mudholkar, 001284732, mudholkar.s@husky.neu.edu
 * Rahul Chandra, 01225683, chandra.ra@husky.neu.edu
 * Manish Patil, 001228956, patil.man@husky.neu.edu
 **/

package edu.neu.csye.tasks.endpoint;

import edu.neu.csye.tasks.endpoint.model.Attachment;
import edu.neu.csye.tasks.endpoint.model.Task;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tasks")
public interface TasksEndpointRest {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Task create(Task task);

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    List<Task> get();
//
//    @Path("/{id}")
//    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    Task update(@PathParam("id") int id);
//
//    @Path("/{id}")
//    @DELETE
//    @Produces(MediaType.APPLICATION_JSON)
//    Response delete(@PathParam("id") int id);
//
//    @Path("/{id}/attachments")
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    List<Attachment> getAttachments(@PathParam("id") int id);
//
//    @Path("/{id}/attachments")
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    Attachment createAttachment (@PathParam("id") int id, Attachment attachment);
//
//    @Path("/{id}/attachments/{idAttachments}")
//    @DELETE
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    Response deleteAttachment(@PathParam("id") int id, @PathParam("idAttachments") int idAttachments);

}
