package com.restapi.RestAPILesson.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddStudentDTO {

    @NotBlank
    @Size(min=3, max=30, message = "Name should be 3-30 characters long!")
    private String name;
    private String city;

    @Email
    @NotBlank(message = "Email is required!")
    private String email;

    @Min(value=0, message = "Marks must be atleast 0")
    @Max(value=100, message="Marls cannot be greater than 100")
    private Long marks;
}

