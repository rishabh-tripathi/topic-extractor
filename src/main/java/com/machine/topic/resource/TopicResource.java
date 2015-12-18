package com.machine.topic.resource;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.machine.topic.model.EntityMatch;
import com.machine.topic.service.TopicService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by rishabh.tripathi on 11/9/15.
 */
@Singleton
@Path("/v1/topic")
public class TopicResource {

    private TopicService topicService;

    @Inject
    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    @Path("/hello")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response helloWorld() {
        return Response.ok("Hello World!").build();
    }

    @Path("/get")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTopic(@QueryParam("id") String id, @QueryParam("text") String text) {
        try {
            List<EntityMatch> res = topicService.getTopicsFromText(text);
            return Response.ok(res).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
