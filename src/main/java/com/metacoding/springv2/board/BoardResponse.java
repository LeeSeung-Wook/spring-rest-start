package com.metacoding.springv2.board;

import lombok.Data;

public class BoardResponse {

    @Data
    public static class List {
        private Integer id;
        private String title;

        public List(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
        }
    }

    @Data
    public static class Detail {
        private Integer id;
        private String title;
        private String content;
        private Integer userId;
        private String username;
        private boolean isBoardOwner;

        public Detail(Board board, int sessionUserId) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.userId = board.getUser().getId();
            this.username = board.getUser().getUsername(); // lazy loading
            this.isBoardOwner = board.getUser().getId() == sessionUserId;
        }
    }
}
