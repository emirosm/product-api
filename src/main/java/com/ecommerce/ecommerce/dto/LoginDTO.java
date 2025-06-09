package com.ecommerce.ecommerce.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDTO {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
