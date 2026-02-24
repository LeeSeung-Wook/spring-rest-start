package com.metacoding.springv2.board;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metacoding.springv2._core.handler.ex.Exception404;
import com.metacoding.springv2.user.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    // id, title
    @Transactional(readOnly = true)
    public List<BoardResponse.List> 게시글목록() {
        List<Board> boards = boardRepository.findAll();
        return boards.stream()
                .map(board -> new BoardResponse.List(board))
                .toList();
    }

    // tr=read, osiv=f, lazy전략
    // id, title, content, userId, username
    @Transactional(readOnly = true)
    public BoardResponse.Detail 게시글상세(int id, int sessionUserId) {
        Board board = boardRepository.findByIdWithUser(id)
                .orElseThrow(() -> new Exception404("자원을 찾을 수 없어요"));

        return new BoardResponse.Detail(board, sessionUserId);
    }

    @Transactional
    public void 게시글작성(BoardRequest.SaveDTO reqDTO, int sessionUserId) {
        User user = User.builder().id(sessionUserId).build();
        Board board = Board.builder()
                .title(reqDTO.getTitle())
                .content(reqDTO.getContent())
                .user(user)
                .build();

        boardRepository.save(board);
    }

    @Transactional
    public void 게시글수정(int id, BoardRequest.UpdateDTO reqDTO, int sessionUserId) {
        Board board = boardRepository.findByIdWithUser(id)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다"));

        board.update(reqDTO.getTitle(), reqDTO.getContent());
    }

    @Transactional
    public void 게시글삭제(int id) {
        boardRepository.deleteById(id);
    }

}