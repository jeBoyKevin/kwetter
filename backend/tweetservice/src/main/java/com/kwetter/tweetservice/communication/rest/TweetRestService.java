package com.kwetter.tweetservice.communication.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.kwetter.tweetservice.manager.TweetManager;

@Path("")
public class TweetRestService {

    private TweetManager manager = new TweetManager();
    private ObjectMapper objectMapper = new ObjectMapper();

    @POST
    @Path("/tweet")
    public Response tweet() throws JsonProcessingException {
        return Response.ok()
                .entity(objectMapper.writeValueAsString(manager.tweet()))
                .build();
    }
}
