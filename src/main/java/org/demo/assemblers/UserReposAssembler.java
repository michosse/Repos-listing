package org.demo.assemblers;

import org.demo.DTOs.Branch;
import org.demo.DTOs.Repo;
import org.demo.DTOs.GetUserReposResponse;

import java.util.List;
import java.util.stream.Collectors;

public class UserReposAssembler {
    public static GetUserReposResponse.UserRepo toUserRepo(Repo repo, List<Branch> branches){
        return new GetUserReposResponse.UserRepo(
                repo.name(),
                repo.owner().login(),
                branches.stream().map(b-> new GetUserReposResponse.Branch(b.name(), b.commit().sha()))
                        .collect(Collectors.toList())
        );
    }
}
