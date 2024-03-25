package com.example.end.dto;
import com.example.end.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "DTO representing a shopping cart")
public class CartDto {

    @Schema(description = "Unique identifier of the cart", example = "1")
    private Long id;

    @Schema(description = "User associated with the cart")
    private User user;

    @Schema(description = "List of procedures in the cart")
    private List<ProcedureDto> procedures;

}


