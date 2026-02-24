package com.metacoding.springv2.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class BoardRequest {

    @Data
    public static class SaveDTO {
        @NotBlank
        @Size(max = 30)
        private String title;

        @NotBlank
        @Size(max = 300)
        private String content;
    }

    @Data
    public static class UpdateDTO {
        @NotBlank
        @Size(max = 30)
        private String title;

        @NotBlank
        @Size(max = 300)
        private String content;
    }
}
