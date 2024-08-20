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
    description = "JWTAuthResponse Model Information"
)
public class JWTAuthResponse {
    @Schema(
        description = "JWTAuthResponse Access Token"
    )
    private String acessToken;

    @Schema(
        description = "JWTAuthResponse TokenType"
    )
    private String tokenType = "Bearer";
}
