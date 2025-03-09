package org.demo.DTOs;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class Repo {
    @Getter
    @Setter
    public static class Owner{
        private String login;
    }
    private String name;
    private Owner owner;
    private boolean fork;

}
