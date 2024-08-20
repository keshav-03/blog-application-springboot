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
    description = "RegisterDTO Model Information"
)
public class RegisterDTO {
    @Schema(
        description = "Register Name field"
    )
    private String name;

    @Schema(
        description = "Register Username field"
    )
    private String username;

    @Schema(
        description = "Register Email field"
    )
    private String email;

    @Schema(
        description = "Register Password field"
    )
    private String password;
}
