package si.fri.project.image_upload.services;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.fri.project.image_upload.models.ImageEntity;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@ApplicationScoped
public class ImageBean {
//    private Logger log = Logger.getLogger(PhotoBean.class.getName());

    @Inject
    private EntityManager em;

    @Inject
    private AppProperties appProperties;

    private Client httpClient;

    @PostConstruct
    private void init() {
        httpClient = ClientBuilder.newClient();
    }

    public List<ImageEntity> getPhotos(UriInfo uriInfo) {

        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery())
                .defaultOffset(0)
                .build();
        if(appProperties.isExternalServicesEnabled()) {
            try {
                return JPAUtils.queryEntities(em, ImageEntity.class, queryParameters);

            } catch (WebApplicationException | ProcessingException e) {
                throw new InternalServerErrorException(e);
            }
        }
        return null;

    }

    public ImageEntity getPhoto(Integer photoId) {
        ImageEntity photo = em.find(ImageEntity.class, photoId);

        if(photo == null) throw new NotFoundException();
        return photo;
    }




    private void beginTx() {
        if (!em.getTransaction().isActive())
            em.getTransaction().begin();
    }

    private void commitTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().commit();
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().rollback();
    }
}
