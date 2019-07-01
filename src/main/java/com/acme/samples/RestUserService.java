package com.acme.samples;

import org.exoplatform.services.rest.resource.ResourceContainer;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.*;

/**
 * Rest User Service!
 */
@Path("/demo")
@Produces("application/json")
public class RestUserService implements ResourceContainer {
    private List<Map<String, String>> tipFromDb = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{
            put("id", "1");
            put("text", "Do not interact with people who are not very fit for you.");
        }});
        add(new HashMap<String, String>() {{
            put("id", "2");
            put("text", "Do not be afraid to take risks, you can change everything.");
        }});
        add(new HashMap<String, String>() {{
            put("id", "3");
            put("text", "Nobody understands what he does: just remember this.");
        }});
    }};


    @GET
    @Path("/tip")
    public Map<String, String> getSomeTip() {
        int index = new Random().nextInt(tipFromDb.size());
        return tipFromDb.get(index);

    }

    @POST
    @Path("/tip")
    public Map<String, String> addNewTip(Map<String, String> tip) {
        Map<String, String> map = null;
        if ((tip.get("text").trim()).equals("")){
            tip.put("text","You mast something write.");
            return tip;
        }

        if (map!=null) return map;
        tip.put("id", String.valueOf(tipFromDb.size() + 1));
        tipFromDb.add(tip);
        return tip;
    }

    @GET
    @Path("/tips")
    public List<Map<String, String>> getAllTip() {
        return tipFromDb;
    }

}