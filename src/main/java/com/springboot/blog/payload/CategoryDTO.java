package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(
    description = "CategoryDTO Model Information"
)
public class CategoryDTO {
    @Schema(
        description = "CategoryDTO Id"
    )
    private Long id;

    @Schema(
        description = "CategoryDTO Name"
    )
    private String name;

    @Schema(
        description = "CategoryDTO Description"
    )
    private String description;
}
