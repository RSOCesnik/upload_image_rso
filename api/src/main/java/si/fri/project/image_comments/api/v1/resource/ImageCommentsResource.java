package si.fri.project.image_comments.api.v1.resource;


import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
@Path("/comments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ImageCommentsResource {

    @GET
    public Response getComments() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> clani = new ArrayList<String>();
        clani.add("bc1610");

        List<String> mikrostoritve = new ArrayList<String>();
        mikrostoritve.add("");

        List<String> github = new ArrayList<String>();
        github.add("https://github.com/RSOCesnik/upload_image_rso");


        List<String> travis = new ArrayList<String>();
        travis.add("https://travis-ci.org/RSOCesnik/upload_image_rso");

        List<String> dockerhub = new ArrayList<String>();
        dockerhub.add("bc1610");


        map.put("clani", clani);
        map.put("opis_projekta", "S pomocjo nasega projekta, lahko dodajate slike, jih gledate ali komentirate");
        map.put("mikrostoritve", mikrostoritve);
        map.put("github", github);
        map.put("travis", travis);
        map.put("dockerhub", dockerhub);
        return Response.ok(map).build();
    }
}
