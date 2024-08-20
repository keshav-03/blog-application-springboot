package com.springboot.blog.payload;

import lombok.Data;
import java.util.*;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Data
@Schema(
    description = "PostDTO Model Information"
)
public class PostDTO {
    private long id;

    //title should not be null and have at least 3 characters
    @Schema(
        description = "Blog Post Title"
    )
    @NotEmpty
    @Size(min = 3, message = "Post title should have at least 3 characters")
    private String title;

    //description should not be null or empty and at least 10 characterss
    @Schema(
        description = "Blog Post Description"
    )
    @NotEmpty
    @Size(min = 10, message = "Post description should have at least 10 characters")
    private String description;

    @NotEmpty
    @Schema(
        description = "Blog Post Content"
    )
    private String content;
    private Set<CommentDTO> comments;

    @Schema(
        description = "Blog Post Post Category Id"
    )
    private Long categoryId;
}
