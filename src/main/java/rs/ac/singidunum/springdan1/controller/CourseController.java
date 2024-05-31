package rs.ac.singidunum.springdan1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.springdan1.entity.Course;
import rs.ac.singidunum.springdan1.model.NameModel;
import rs.ac.singidunum.springdan1.service.CourseService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/course")
@RequiredArgsConstructor
@CrossOrigin
public class CourseController {

    private final CourseService service;

    @GetMapping
    public List<Course> getAll() {
        return service.getAllCourses();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Course> getById(@PathVariable Integer id) {
        return ResponseEntity.of(service.getCourseById(id));
    }

    @PostMapping
    public Course create(@RequestBody NameModel model) {
        return service.saveCourse(model);
    }

    @PutMapping(path = "/{id}")
    public Course update(@PathVariable Integer id, @RequestBody NameModel model) {
        return service.updateCourse(id, model);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.deleteCourse(id);
    }
}
