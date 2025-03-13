package org.demo.DTOs;


public record Repo(String name, org.demo.DTOs.Repo.Owner owner, boolean fork) {

    public record Owner(String login) {
    }
}
