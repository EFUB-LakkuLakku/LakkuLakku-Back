package com.efub.lakkulakku.domain.comments.controller;

import com.efub.lakkulakku.domain.comments.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/diaries")
public class CommentsController {

    @Autowired
    private final CommentsService commentsService;

    @PostMapping("/{date}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity commentsSave(@PathVariable UUID id, @RequestBody CommentsRequestDto dto /*, @LoginUser UserSessionDto userSessionDto*/) {
        return ResponseEntity.ok(commentsService.commentsSave(id, dto));
    }


    @PostMapping("/{date}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UUID> recommentsSave(@PathVariable UUID id, @PathVariable UUID parentId, @RequestBody CommentsRequestDto dto /*, @LoginUser UserSessionDto userSessionDto*/) {
        return ResponseEntity.ok(commentsService.recommentsSave(id, parentId, dto));
    }


    @PutMapping("/{date}/comments")
    public ResponseEntity update(@PathVariable UUID id, @RequestBody CommentsRequestDto dto) {
        commentsService.update(id, dto);
        return ResponseEntity.ok(id);
    }


    @DeleteMapping("/{date}/comments")
    public ResponseEntity delete(@PathVariable UUID id) {
        commentsService.delete(id);
        return ResponseEntity.ok(id);
    }

}