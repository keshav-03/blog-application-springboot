package com.springboot.blog.service;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.payload.PostResponse;

public interface PostService {
    PostDTO createPost(PostDTO postDTO);  
    
    PostResponse getAllPosts(int pageSize, int pageNo, String sortBy, String sortDir);

    PostDTO getPostById(long id);

    PostDTO updatePost(PostDTO postDTO, long id);
    
    void deletePostById(long id);

    List<PostDTO> getPostsByCategory(Long categoryId);
} 
