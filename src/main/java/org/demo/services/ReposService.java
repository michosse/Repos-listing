package org.demo.services;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.WebApplicationException;
import org.demo.DTOs.Branch;
import org.demo.DTOs.Repo;
import org.demo.DTOs.GetUserReposResponse;
import org.demo.assemblers.UserReposAssembler;
import org.demo.clients.GhRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ReposService {
    @RestClient
    @Inject
    GhRestClient ghRestClient;

    public Uni<List<Repo>> getUsersPublicRepos(String username){
        return ghRestClient.getUsersPublicRepos(username).map(repos-> repos.stream().filter(r -> !r.fork()).collect(Collectors.toList()));
    }

    public Uni<List<Branch>> getReposBranches(String username, String reponame){
        return ghRestClient.getReposBranches(username,reponame);
    }

    public Uni<List<GetUserReposResponse.UserRepo>> getUsersReposWithBranches(String username){
        return getUsersPublicRepos(username)
            .onFailure(WebApplicationException.class).transform(e -> new NotFoundException())
            .onItem().transformToUni(reposApiResponse ->
                Multi.createFrom().iterable(reposApiResponse)
                    .onItem().transformToUniAndMerge(repo ->
                        getReposBranches(username, repo.name())
                            .onItem().transform(branches -> UserReposAssembler.toUserRepo(repo, branches))
                    )
                    .collect().asList()
        );
    }

}
