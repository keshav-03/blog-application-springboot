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
    description = "LoginDTO Model Information"
)
public class LoginDTO {
    @Schema(
        description = "LoginDTO UsernamOrEmail Field"
    )
    private String usernameOrEmail;

    @Schema(
        description = "LoginDTO Password Field"
    )
    private String password;
}
