package com.springboot.blog.payload;
import java.util.*;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        description = "PostResponse Model Information"
    )
public class PostResponse {
    @Schema(
        description = "List of Content"
    )
    private List<PostDTO> content;

    @Schema(
        description = "Query PageNo paramter"
    )
    private int pageNo;

    @Schema(
        description = "Query PageSize paramter"
    )
    private int pageSize;

    @Schema(
        description = "Response Total ELements"
    )
    private long totalElements;

    @Schema(
        description = "Response Toal Pages"
    )
    private int totalPages;

    @Schema(
        description = "Response Check Last Item"
    )
    private boolean last;

}
