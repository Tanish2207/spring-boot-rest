package com.restapi.RestAPILesson.service.impl;

import com.restapi.RestAPILesson.dto.AddStudentDTO;
import com.restapi.RestAPILesson.dto.StudentDTO;
import com.restapi.RestAPILesson.entity.Student;
import com.restapi.RestAPILesson.repository.StudentRepository;
import com.restapi.RestAPILesson.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> studentsList = studentRepository.findAll();
        List<StudentDTO> studentDTOList = studentsList
                .stream()
                .map(student -> new StudentDTO(student.getId(), student.getName(), student.getCity(), student.getEmail(), student.getMarks())).toList();

        return studentDTOList;
    }

    @Override
    public StudentDTO getStudentById(UUID id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("StudentID: " + id + " not found"));
        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getCity(),
                student.getEmail(),
                student.getMarks()
        );
    }

    @Override
    public StudentDTO createNewStudent(AddStudentDTO addStudentDTO) {
        Student newStudent = modelMapper.map(addStudentDTO, Student.class);
        Student student = studentRepository.save(newStudent);
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public void deleteStudentById(UUID sid) {
        if (!studentRepository.existsById(sid)) {
            throw new IllegalArgumentException("No such student exists!!");
        }
        studentRepository.deleteById(sid);
    }

    @Override
    public StudentDTO updateStudent(UUID sid, AddStudentDTO addStudentDTO) {
        Student student = studentRepository.findById(sid).orElseThrow(() -> new IllegalArgumentException("StudentID: " + sid + " not found"));
        modelMapper.map(addStudentDTO, student);

        student = studentRepository.save(student);
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public StudentDTO partialUpdateStudent(UUID sid, Map<String, Object> updates) {
        Student student = studentRepository.findById(sid).orElseThrow(() -> new IllegalArgumentException("StudentID: " + sid + " not found"));

        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    student.setName((String) value);
                    break;
                case "city":
                    student.setCity((String) value);
                    break;
                case "email":
//                    if (((String) value).contains("@")) {
                        student.setEmail((String) value);
//                    } else {
//                        throw new IllegalArgumentException("Email format invalid!!");
//                    }
                    break;
                case "marks":
//                    long marks = c;
//                    if (marks <= 200) {
                        student.setMarks(((Number) value).longValue());
//                    } else {
//                        throw new IllegalArgumentException("Marks invalid!!");
//                    }
//                    No need of this validation now, I have added the Jakarata Validation Contraints in DTO
                    break;
            }
        });
        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentDTO.class);

    }
}

