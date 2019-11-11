package si.fri.project.image_comments.api.v1.resource;


import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/comments")

public class ImageCommentsResource {

    @GET
    public Response getComments() {
        return Response.ok("Comments service is working.").build();
    }
}
