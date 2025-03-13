package org.demo.DTOs;

public record Branch(String name, org.demo.DTOs.Branch.Commit commit) {

    public record Commit(String sha) {
    }
}
