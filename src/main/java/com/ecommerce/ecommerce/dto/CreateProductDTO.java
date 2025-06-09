package com.ecommerce.ecommerce.dto;


import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductDTO {

    @NotBlank
    private String name;

    private String description;

    @DecimalMin("0.0")
    private double price;

    @NotNull
    private Long categoryId;
}
