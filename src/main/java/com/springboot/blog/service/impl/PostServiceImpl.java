package com.springboot.blog.service.impl;
import java.util.*;
import java.util.stream.Collectors;

import org.hibernate.mapping.Collection;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.blog.entity.Category;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDTO;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.repository.CategoryRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService{

    private PostRepository postRepository;
    private ModelMapper mapper;
    private CategoryRepository categoryRepository;

    public PostServiceImpl(PostRepository postRepository, ModelMapper mapper, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
    } 

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Category category = categoryRepository.findById(postDTO.getCategoryId())
            .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", postDTO.getCategoryId()));
        

        //convert DTO to entity
        Post post = mapDTOtoEntity(postDTO);
        post.setCategory(category);

        Post newPost = postRepository.save(post);

        //convert entity to DTO
        PostDTO postResponse = mapEntityToDTO(newPost);
        return postResponse;
    }
    
    @Override
    public PostResponse getAllPosts(int pageSize, int pageNo, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                    : Sort.by(sortBy).descending();


        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> pagePosts = postRepository.findAll(pageable);

        List<Post> listOfPosts = pagePosts.getContent();
        List<PostDTO> content = listOfPosts.stream().map(post -> mapEntityToDTO(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(pagePosts.getNumber());
        postResponse.setPageSize(pagePosts.getSize());
        postResponse.setTotalElements(pagePosts.getTotalElements());
        postResponse.setTotalPages(pagePosts.getTotalPages());
        postResponse.setLast(pagePosts.isLast());

        return postResponse;
    }


    private PostDTO mapEntityToDTO(Post post){
        PostDTO postDTO = mapper.map(post, PostDTO.class);
        // postDTO.setId(post.getId());
        // postDTO.setTitle(post.getTitle());
        // postDTO.setDescription(post.getDescription());
        // postDTO.setContent(post.getContent());

        return postDTO;
    }

    private Post mapDTOtoEntity(PostDTO postDTO) {
        Post post = mapper.map(postDTO, Post.class);
        // post.setTitle(postDTO.getTitle());
        // post.setDescription(postDTO.getDescription());
        // post.setContent(postDTO.getContent());
        return post;
    }

    @Override
    public PostDTO getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Post", "id", id)
        );
        return mapEntityToDTO(post);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, long id) {
        Post post = postRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Post", "id", id)
        );

        Category category = categoryRepository.findById(postDTO.getCategoryId())
                            .orElseThrow(() -> new ResourceNotFoundException("Category", "id", postDTO.getCategoryId()));
        

        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());
        post.setCategory(category);
        Post updatedPost = postRepository.save(post);
        return mapEntityToDTO(updatedPost);
    }

    @Override
    public void deletePostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Post", "id", id)
        );

        postRepository.delete(post);
    }

    @Override
    public List<PostDTO> getPostsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                            .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
        List<Post> posts = postRepository.findByCategoryId(categoryId);
        return posts.stream().map((post) -> mapEntityToDTO(post)).collect(Collectors.toList());
    }
}
