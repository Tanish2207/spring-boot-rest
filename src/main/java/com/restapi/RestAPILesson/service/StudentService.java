package com.restapi.RestAPILesson.service;

import com.restapi.RestAPILesson.dto.AddStudentDTO;
import com.restapi.RestAPILesson.dto.StudentDTO;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface StudentService {
    List<StudentDTO> getAllStudents();

    StudentDTO getStudentById(UUID id);

    StudentDTO createNewStudent(AddStudentDTO addStudentDTO);

    void deleteStudentById(UUID sid);

    StudentDTO updateStudent(UUID sid, AddStudentDTO addStudentDTO);

    StudentDTO partialUpdateStudent(UUID sid, Map<String, Object> updates);
}
