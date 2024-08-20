package com.springboot.blog.payload;

import org.hibernate.annotations.SortComparator;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
    description = "CommentDTO Model Information"
)
public class CommentDTO {

    @Schema(
        description = "CommentDTO Id"
    )
    private long id;

    //name should not be null or empty
    @Schema(
        description = "CommentDTO Name"
    )
    @NotEmpty(message = "Name should not be null or empty")
    private String name;

    //email should not be null or empty
    //email validation
    @Schema(
        description = "CommentDTO Email"
    )
    @NotEmpty(message = "Email should not be null or empty")
    @Email
    private String email;

    //body should not be null or empty and have at least 10 characters
    @Schema(
        description = "CommentDTO Comment Body"
    )
    @NotEmpty
    @Size(min = 10, message = "Comment body must be minimum 10 characters")
    private String body;
}
