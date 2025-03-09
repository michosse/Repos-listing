package org.demo.controllers;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.demo.DTOs.GetUserReposResponse;
import org.demo.exceptions.ErrorMessage;
import org.demo.services.ReposService;

@Path("/{username}")
public class ReposController {
    @Inject
    ReposService reposService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> getAllUserRepos(@PathParam("username") String username){
        return reposService.getUsersReposWithBranches(username).map(r -> Response.ok(new GetUserReposResponse(r)).build())
                .onFailure(NotFoundException.class)
                .recoverWithItem(() -> Response
                        .status(Response.Status.NOT_FOUND)
                        .entity(new ErrorMessage(404,"User not found"))
                        .build());
    }
}
