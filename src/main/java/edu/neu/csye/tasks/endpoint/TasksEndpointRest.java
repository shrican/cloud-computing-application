/**
 * Varsha Bhanushali, 001234580,
 * Shrikant Mudholkar, 001284732,
 * Rahul Chandra, 01225683,
 * Manish Patil, 001228956,
 **/

package edu.neu.csye.tasks.endpoint;

import edu.neu.csye.tasks.dataaccess.model.AttachmentEntity;
import edu.neu.csye.tasks.dataaccess.model.TaskEntity;
import edu.neu.csye.tasks.endpoint.model.Attachment;
import edu.neu.csye.tasks.endpoint.model.Task;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.Set;

@Path("/tasks")
public interface TasksEndpointRest {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Task create(TaskEntity task);

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

    @Path("/{taskId}/attachments")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Set<Attachment> getAttachments(@PathParam("taskId") String taskId);


    @Path("/{taskId}/attachments/{idAttachments}")
    @DELETE
    Response deleteAttachment(@PathParam("taskId") String taskId, @PathParam("idAttachments") String idAttachments);

    @Path("/{id}/attachments")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    AttachmentEntity createAttachment(@PathParam("id") String id, @FormDataParam("file") InputStream fileInputStream,
                                      @FormDataParam("file") FormDataContentDisposition cdh);

}