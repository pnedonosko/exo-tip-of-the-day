package com.example;

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
        add(new HashMap<String, String>() {{
            put("id", "4");
            put("text", "Start the day with a smile: nothing is charging a better one.");
        }});
        add(new HashMap<String, String>() {{
            put("id", "5");
            put("text", "Make your day-trips in the outdoors.");
        }});
        add(new HashMap<String, String>() {{
            put("id", "6");
            put("text", "Build a week with a vegetarian. And suddenly you like it?");
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
        if ((tip.get("text").trim()).equals("")){
            tip.put("text","You mast something write.");
            return tip;
        }
        Map<String,String> map = tipFromDb.stream().filter(oneTip -> oneTip.get("text").equals(tip.get("text"))).findFirst().orElse(null);
        if (map == null){
            tip.put("id", String.valueOf(tipFromDb.size() + 1));
            tipFromDb.add(tip);
            return tip;
        } else {
            return map;
        }

    }

    @GET
    @Path("/tips")
    public List<Map<String, String>> getAllTip() {
        return tipFromDb;
    }

}