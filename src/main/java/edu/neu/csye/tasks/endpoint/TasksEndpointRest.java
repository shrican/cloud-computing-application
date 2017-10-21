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
import java.util.List;

@Path("/tasks")
public interface TasksEndpointRest {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Task create(Task task);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<Task> get();

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Task update(Task task);

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    Task delete(@PathParam(id) int id);

    @Path("/{id}/attachments")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<Attachment> getAttachments(Attachment attachment);

    @Path("/{id}/attachments")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    List<Attachment> createAttachment(Attachment attachment);

    @Path("/{id}/attachments/{attachmentid}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    List<Attachment> deleteAttachment(@PathParam(attachmentid) int attachmentid);







}
