package si.fri.project.image_upload.services;

import si.fri.project.image_upload.models.ImageEntity;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.logging.Logger;

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

    private Logger log = Logger.getLogger(ImageBean.class.getName());

    public ImageEntity createPhoto(ImageEntity photo) {
        try{
            beginTx();
            em.persist(photo);
            commitTx();
            log.info("Successfully saved new photo");
        } catch (Exception e) {
            log.warning("There was a problem with saving new photo");
            log.warning(e.getMessage());
            rollbackTx();
        }
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
