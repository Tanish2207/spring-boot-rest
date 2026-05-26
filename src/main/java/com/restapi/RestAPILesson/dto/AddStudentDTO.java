package com.restapi.RestAPILesson.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddStudentDTO {
    @Nullable
    private String name;
    private String city;
    private String email;
    private Long marks;
}

