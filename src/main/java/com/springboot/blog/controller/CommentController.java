package com.springboot.blog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payload.CommentDTO;
import com.springboot.blog.service.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.*;

@RestController
@RequestMapping("/api/v1")
@Tag(
    name = "CRUD REST APIs for Comment Resource"
)
public class CommentController {
    private CommentService commentService;


    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //Create Comment Rest APIs
    @Operation(
        summary = "Create Comment REST API",
        description = "Create Comment REST API is used to save comment into database"
    )
    @ApiResponse(
        responseCode = "201",
        description = "Http Status 201 CREATED"
    )
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@PathVariable(value = "postId") long postId, 
                                                        @Valid @RequestBody CommentDTO commentDTO) {

        return new ResponseEntity<>(commentService.createComment(postId, commentDTO), HttpStatus.CREATED);
    }

    //Get comment by PostID REST API
    @Operation(
        summary = "GetCommentByPostId Comment REST API",
        description = "Get Comment By PostId Comment REST API is used get all the comments of that particular postId from the database"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDTO> getCommentsByPostId(
        @PathVariable(value = "postId") long postId
    ) {
        return commentService.getCommentsByPostId(postId);
    }
    
    //get comment by postId and commentId REST API
    @Operation(
        summary = "GetCommentById Comment REST API",
        description = "Get Comment By Id  REST API is used get the comment of that particualar commentId from the database"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Http Status 200 SUCCESS"
    )
    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDTO> getCommentById(
        @PathVariable(value = "postId") Long postId,
        @PathVariable(value = "id") Long commentId
    ) {
        CommentDTO commentDTO = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }

    //update comment REST API
    @Operation(
        summary = "Update Comment REST API",
        description = "Update Comment  REST API is used update the comment by using postId and commentId into the database"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Http Status 200 SUCCESS"
    )
    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDTO> updateComment(
        @PathVariable(value = "postId") Long postId,
        @PathVariable(value = "id") Long commentId,
        @Valid @RequestBody CommentDTO commentDTO
    ) {
        CommentDTO updatedCommentDTO = commentService.updateComment(postId, commentId, commentDTO);
        return new ResponseEntity<>(updatedCommentDTO, HttpStatus.OK);
    }
    
    //Delete Comment REST API
    @Operation(
        summary = "Delete Comment REST API",
        description = "Delete Comment REST API is used to delete the comment by using postId and commentId from the database"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Http Status 200 SUCCESS"
    )
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(
        @PathVariable(value = "postId") Long postId,
        @PathVariable(value = "id") Long commentId
    ) {
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
}
