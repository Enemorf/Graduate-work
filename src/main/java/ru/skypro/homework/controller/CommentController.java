package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.service.CommentService;

import java.security.Principal;
import java.util.NoSuchElementException;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class CommentController {

    private final CommentService commentService;
    @GetMapping("/{id}/comments")
    public ResponseEntity<CommentsDto> getAdComments(@PathVariable Integer id)
    {
        log.info("Get getAdComments");
        CommentsDto commentsDto = commentService.getAdComments(id);
        return ResponseEntity.ok().body(commentsDto);
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDto> addComment(@PathVariable Integer id,
                                                 @RequestBody CreateOrUpdateCommentDto createOrUpdateCommentDto,
                                                 Principal principal)
    {
        log.info("Post addComment");
        CommentDto commentDto = commentService.addComment(id,createOrUpdateCommentDto,principal);
        return ResponseEntity.ok().body(commentDto);
    }

    @DeleteMapping("/{adId}/comments/{commentId}")
    @PreAuthorize("@roleAccess.adminOwnerComment(#commentId, authentication)")
    public ResponseEntity<CommentDto> removeComment(@PathVariable Integer adId,
                                                    @PathVariable Integer commentId)
    {
        log.info("Delete removeComment");
        try
        {
            commentService.removeComment(commentId);
            return ResponseEntity.ok().build();
        }
        catch (NoSuchElementException e)
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PatchMapping("/{adId}/comments/{commentId}")
    @PreAuthorize("@roleAccess.adminOwnerComment(#commentId, authentication)")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Integer adId,
                                                    @PathVariable Integer commentId,
                                                    @RequestBody CreateOrUpdateCommentDto createOrUpdateCommentDto)
    {
        log.info("Patch updateComment");
        CommentDto commentDto = commentService.updateComment(adId,commentId,createOrUpdateCommentDto);
        return ResponseEntity.ok().body(commentDto);
    }
}
