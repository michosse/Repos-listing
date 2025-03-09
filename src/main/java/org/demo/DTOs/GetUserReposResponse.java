package org.demo.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetUserReposResponse {

    @Setter
    @Getter
    @Builder
    public static class Branch{
        private String name;
        private String lastCommitSha;
    }
    @Setter
    @Getter
    @Builder
    public static class UserRepo {
        private String repositoryName;
        private String owner;
        List<Branch> branches;
    }
    List<UserRepo> userRepos;
}
