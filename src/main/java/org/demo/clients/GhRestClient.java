package org.demo.clients;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.demo.DTOs.Branch;
import org.demo.DTOs.Repo;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(configKey = "gh-client")
public interface GhRestClient {
    @GET
    @Path("users/{username}/repos")
    Uni<List<Repo>> getUsersPublicRepos(@PathParam("username") String username);

    @GET
    @Path("repos/{username}/{reponame}/branches")
    Uni<List<Branch>> getReposBranches(@PathParam("username") String username, @PathParam("reponame") String reponame);
}
