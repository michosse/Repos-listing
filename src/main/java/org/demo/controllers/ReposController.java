package org.demo.controllers;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.demo.DTOs.GetUserReposResponse;
import org.demo.exceptions.HttpException;
import org.demo.services.ReposService;

@Path("/{username}")
@ApplicationScoped
public class ReposController {
    @Inject
    ReposService reposService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<GetUserReposResponse> getAllUserRepos(@PathParam("username") String username){
        return reposService.getUsersReposWithBranches(username).map(GetUserReposResponse::new)
                .onFailure(NotFoundException.class)
                .transform(e -> new HttpException(404, "User not found"));
    }
}
