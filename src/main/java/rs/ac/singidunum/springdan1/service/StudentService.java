package rs.ac.singidunum.springdan1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.springdan1.entity.Course;
import rs.ac.singidunum.springdan1.entity.Student;
import rs.ac.singidunum.springdan1.entity.StudyProgramme;
import rs.ac.singidunum.springdan1.model.StudentModel;
import rs.ac.singidunum.springdan1.model.ToggleCourse;
import rs.ac.singidunum.springdan1.repository.StudentRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;
    private final StudyProgrammeService studyProgrammeService;

    public List<Student> getAllStudents() {
        return repository.findAllByDeletedAtIsNull();
    }

    public Optional<Student> getStudentById(Integer id) {
        return repository.findByIdAndDeletedAtIsNull(id);
    }

    public List<Student> getStudentByIndeks(String indeks) {
        return repository.findByIndeksContainsAndDeletedAtIsNull(indeks);
    }

    public Student createStudent(StudentModel model) {
        StudyProgramme study = studyProgrammeService
                .getStudyProgrammeById(model.getStudyProgrammeId())
                .orElseThrow();
        Student student = new Student();
        student.setName(model.getName());
        student.setSurname(model.getSurname());
        student.setIndeks(model.getIndeks());
        student.setStudyProgramme(study);
        student.setCreatedAt(LocalDateTime.now());
        return repository.save(student);
    }

    public Student updateStudent(Integer id, StudentModel model) {
        Student student = repository.findByIdAndDeletedAtIsNull(id).orElseThrow();
        StudyProgramme study = studyProgrammeService
                .getStudyProgrammeById(model.getStudyProgrammeId())
                .orElseThrow();
        student.setName(model.getName());
        student.setSurname(model.getSurname());
        student.setIndeks(model.getIndeks());
        student.setStudyProgramme(study);
        student.setUpdatedAt(LocalDateTime.now());
        return repository.save(student);
    }

    public void deletedStudent(Integer id) {
        Student student = repository.findByIdAndDeletedAtIsNull(id).orElseThrow();
        student.setDeletedAt(LocalDateTime.now());
        repository.save(student);
    }

    public void toggleCourseForStudentId(Integer id, ToggleCourse model) {
        Student student = repository.findByIdAndDeletedAtIsNull(id).orElseThrow();
        List<Course> studentCourses = student.getCourses();

        boolean contains = studentCourses.stream().map(Course::getId).toList().contains(model.getCourse().getId());
        if (!model.getActive() && contains) {
            studentCourses.remove(studentCourses.stream().filter(c->c.getId() == model.getCourse().getId()).findFirst().get());
            student.setCourses(studentCourses);
            repository.save(student);
        }

        if (model.getActive() && !contains) {
            studentCourses.add(model.getCourse());
            student.setCourses(studentCourses);
            repository.save(student);
        }
    }
}
