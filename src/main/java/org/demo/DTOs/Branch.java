package org.demo.DTOs;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class Branch {
    @Setter
    @Getter
    public static class Commit{
        private String sha;
    }
    private String name;
    private Commit commit;
}
