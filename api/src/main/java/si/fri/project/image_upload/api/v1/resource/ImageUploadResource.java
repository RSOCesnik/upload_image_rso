package si.fri.project.image_upload.api.v1.resource;


import si.fri.project.image_upload.models.ImageEntity;
import si.fri.project.image_upload.services.ImageBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@ApplicationScoped
@Path("/uploads")
public class ImageUploadResource {

    @Context
    private UriInfo uriInfo;

    @Inject
    private ImageBean imageBean;

    @GET
    public Response getPhotos() {
        List<ImageEntity> photos = imageBean.getPhotos(uriInfo);

        return Response.ok(photos).build();
    }

}
