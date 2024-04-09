package com.example.end.controller.api;

import com.example.end.dto.ProcedureDto;
import com.example.end.dto.StandardResponseDto;
import com.example.end.validation.dto.ValidationErrorsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RequestMapping("/api/procedures")
@Tags(value = {
        @Tag(name = "Procedures", description = "Procedure management endpoints")
})
@ApiResponses(value = {
        @ApiResponse(responseCode = "401",
                description = "User not authenticated",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = StandardResponseDto.class))),
        @ApiResponse(responseCode = "403",
                description = "Forbidden",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = StandardResponseDto.class)))
})
public interface ProcedureApi {


    @Operation(summary = "Create a procedure", description = "Available to ADMIN")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Procedure created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProcedureDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Validation error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ValidationErrorsDto.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ProcedureDto createProcedure(@RequestBody @Valid ProcedureDto procedureDto);


    @Operation(summary = "Update a procedure", description = "Available to ADMIN")
    @PutMapping
    void update(@RequestBody @Valid ProcedureDto procedureDto);


    @Operation(summary = "Delete a procedure by ID", description = "Available to ADMIN")
    @DeleteMapping("/{id}")
    ProcedureDto deleteById(@Parameter(description = "procedure id", example = "1")
                            @PathVariable("id") Long id);

    @Operation(summary = "Find a procedure by name", description = "Retrieve a procedure by its name")
    @GetMapping("/name/{name}")
    ProcedureDto findByName(@PathVariable String name);

    @Operation(summary = "Get a list of all procedures", description = "Retrieve all procedures")
    @GetMapping()
    List<ProcedureDto> findAll();

    @Operation(summary = "Find a procedure by ID", description = "Retrieve a procedure by its ID")
    @GetMapping("/{id}")
    ProcedureDto findById(@Parameter(description = "procedure id", example = "1")
            @PathVariable("id") Long id);
}