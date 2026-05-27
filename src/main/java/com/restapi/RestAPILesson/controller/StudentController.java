package com.restapi.RestAPILesson.controller;

import com.restapi.RestAPILesson.dto.AddStudentDTO;
import com.restapi.RestAPILesson.dto.StudentDTO;
import com.restapi.RestAPILesson.service.StudentService;
import com.restapi.RestAPILesson.service.impl.StudentServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/students") --- optional
public class StudentController {

    private final StudentService studentService;

//    @GetMapping("/")
//    public StudentDTO getStudent() {
//        return new StudentDTO(13L, "Tanish", "Thane", "bhamare.tn@gmail.com");
//    }


//    ------------------Connecting Presentation Layer directly with Persistence Layer (Not good practice)------
//    //    public StudentController(StudentRepository studentRepository) {

    //        this.studentRepository = studentRepository;
    //    }
//
//    @GetMapping("/student")
//    public List<Student> getStudent() {
//        return studentRepository.findAll();
//    }

    @GetMapping("/students")
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/students/{sid}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable UUID sid){
        return ResponseEntity.ok(studentService.getStudentById(sid));
//        return ResponseEntity.status(HttpStatus.OK).body(studentService.getStudentById((sid)));
//        both ways valid
    }

    @PostMapping("/students")
    public ResponseEntity<StudentDTO> createNewStudent(@RequestBody @Valid AddStudentDTO addStudentDTO){
//        System.out.println("POST REQUEST VALIDATED!!!!!!");
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createNewStudent(addStudentDTO));
    }

    @DeleteMapping("/students/{sid}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable UUID sid){
        studentService.deleteStudentById(sid);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/students/{sid}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable UUID sid, @RequestBody AddStudentDTO addStudentDTO){
        return ResponseEntity.ok(studentService.updateStudent(sid, addStudentDTO));
    }

    @PatchMapping("/students/{sid}")
    public ResponseEntity<StudentDTO> partialUpdateStudent(@PathVariable UUID sid, @RequestBody Map<String, Object> updates){
        return ResponseEntity.ok(studentService.partialUpdateStudent(sid, updates));
    }

}
