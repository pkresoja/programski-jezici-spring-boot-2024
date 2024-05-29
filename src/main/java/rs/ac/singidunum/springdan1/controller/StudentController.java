package rs.ac.singidunum.springdan1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.springdan1.entity.Student;
import rs.ac.singidunum.springdan1.model.StudentModel;
import rs.ac.singidunum.springdan1.service.StudentService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/student")
@RequiredArgsConstructor
@CrossOrigin
public class StudentController {

    private final StudentService service;

    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAllStudents();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
        return ResponseEntity.of(service.getStudentById(id));
    }

    @GetMapping(path = "/indeks/{indeks}")
    public List<Student> getStudentByIndeks(@PathVariable String indeks) {
        return service.getStudentByIndeks(indeks);
    }

    @PostMapping
    public Student createStudent(@RequestBody StudentModel student) {
        return service.createStudent(student);
    }

    @PutMapping(path = "/{id}")
    public Student updateStudent(@PathVariable Integer id, @RequestBody StudentModel student) {
        return service.updateStudent(id, student);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletedStudent(@PathVariable Integer id) {
        service.deletedStudent(id);
    }
}
