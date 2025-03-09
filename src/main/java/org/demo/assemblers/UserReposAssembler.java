package org.demo.assemblers;

import org.demo.DTOs.Branch;
import org.demo.DTOs.Repo;
import org.demo.DTOs.GetUserReposResponse;

import java.util.List;
import java.util.stream.Collectors;

public class UserReposAssembler {
    public static GetUserReposResponse.UserRepo toUserRepo(Repo repo, List<Branch> branches){
        return GetUserReposResponse.UserRepo.builder()
                .repositoryName(repo.getName())
                .owner(repo.getOwner().getLogin())
                .branches(branches.stream().map(b-> GetUserReposResponse.Branch.builder()
                                .name(b.getName())
                                .lastCommitSha(b.getCommit().getSha())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
