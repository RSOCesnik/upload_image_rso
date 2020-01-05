package si.fri.project.image_upload.api.v1.resource;


import com.kumuluz.ee.discovery.annotations.DiscoverService;
import com.kumuluz.ee.logs.cdi.Log;
import si.fri.project.image_upload.models.ImageEntity;
import si.fri.project.image_upload.services.ImageBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.Optional;

@Log
@ApplicationScoped
@Path("/uploads")
public class ImageUploadResource {

    private Client httpClient;

    @Context
    private UriInfo uriInfo;

    @Inject
    private ImageBean imageBean;

    @Inject
    @DiscoverService(value = "image-catalog-service")
    private Optional<String> imageCatalogServiceUrl;

    private List<ImageEntity> getAllImages(){
        if (imageCatalogServiceUrl.isPresent()) {
            try {
                return httpClient.target(imageCatalogServiceUrl.get() + "/v1/catalog/")
                        .request().get(new GenericType<List<ImageEntity>>() {}
                        );
            } catch (WebApplicationException | ProcessingException e) {
                System.out.println(e.getMessage());
                throw new InternalServerErrorException(e);
            }
        }
        return null;
    };

    @POST
    public Response createPhoto(ImageEntity photo) {
        if(photo.getTitle() == null || photo.getTitle().isEmpty() || photo.getData() == null || photo.getDescription() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        else {
            photo = imageBean.createPhoto(photo);
        }

        if(photo.getId() != null) {
            return Response.status(Response.Status.CREATED).entity(photo).build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity(photo).build();
        }
    }
    @GET
    public Response getPhotos() {
        return Response.ok(imageCatalogServiceUrl.get() + "/v1/catalog/").build();
    }

}
