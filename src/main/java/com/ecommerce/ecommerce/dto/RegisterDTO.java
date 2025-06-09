package com.ecommerce.ecommerce.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterDTO {

    @Email
    @NotBlank
    private String email;

    @Size(min = 6)
    @NotBlank
    private String password;
}
