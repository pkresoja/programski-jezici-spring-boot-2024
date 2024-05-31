package rs.ac.singidunum.springdan1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.springdan1.entity.Course;
import rs.ac.singidunum.springdan1.entity.StudyProgramme;
import rs.ac.singidunum.springdan1.model.NameModel;
import rs.ac.singidunum.springdan1.repository.CourseRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository repository;

    public List<Course> getAllCourses() {
        return repository.findAllByDeletedAtIsNull();
    }

    public Optional<Course> getCourseById(Integer id) {
        return repository.findByIdAndDeletedAtIsNull(id);
    }

    public Course saveCourse(NameModel model) {
        Course course = new Course();
        course.setName(model.getName());
        course.setCreatedAt(LocalDateTime.now());
        return repository.save(course);
    }

    public Course updateCourse(Integer id, NameModel model) {
       Course course = repository.findByIdAndDeletedAtIsNull(id).orElseThrow();
        course.setName(model.getName());
        course.setUpdatedAt(LocalDateTime.now());
        return repository.save(course);
    }

    public void deleteCourse(Integer id) {
        Course course = repository.findByIdAndDeletedAtIsNull(id).orElseThrow();
        course.setDeletedAt(LocalDateTime.now());
        repository.save(course);
    }

}
