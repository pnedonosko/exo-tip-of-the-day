package com.example;

import org.exoplatform.services.rest.resource.ResourceContainer;

import javax.ws.rs.*;
import java.util.*;

/**
 * Rest User Service!
 */
@Path("/demo")
@Produces("application/json")
public class RestUserService implements ResourceContainer {
    private List<Map<String, String>> tips = new ArrayList<Map<String, String>>() {{
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


    @POST
    @Path("/tip")
    public Map<String, String> addNewTip(Map<String, String> tip) {
        if (tip.get("text") == null || (tip.get("text").trim()).equals("")){
            tip.put("text","You mast something write.");
            return tip;
        }
        Map<String,String> map = tips.stream()
                .filter(oneTip -> oneTip.get("text").equals(tip.get("text")))
                .findFirst()
                .orElse(null);
        if (map == null){
            tip.put("id", String.valueOf(tips.size() + 1));
            tips.add(tip);
            return tip;
        } else {
            return map;
        }
    }

    @GET
    @Path("/tip")
    public List<Map<String, String>> getAllTip() {
        return tips;
    }

    @GET
    @Path("/tip/{id}")
    public Map<String, String> getTip(@PathParam("id") String id) {
        return findTipById(id);
    }

    @PUT
    @Path("/tip/{id}")
    public Map<String, String> updateTip(@PathParam("id") String id, Map<String, String> tip){
        Map<String, String> tipFromDb = findTipById(id);
        tipFromDb.putAll(tip);
        tipFromDb.put("id", id);
        return tipFromDb;
    }

    @DELETE
    @Path("/tip/{id}")
    public void delete(@PathParam("id") String id){
        Map<String, String> tipFromDb = findTipById(id);
        tips.remove(tipFromDb);
    }

    @GET
    @Path("/random-tip")
    public Map<String, String> getSomeTip() {
        int index = new Random().nextInt(tips.size());
        return tips.get(index);

    }

    private Map<String, String> findTipById(String id) {
        return tips.stream()
                .filter(oneTip -> oneTip.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }
}