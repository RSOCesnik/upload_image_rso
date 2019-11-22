package si.fri.project.image_upload.api.v1.resource;


import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/uploads")
public class ImageUploadResource {

    @GET
    public Response getComments() {
        return Response.ok("UPLOADER API").build();
    }
}
