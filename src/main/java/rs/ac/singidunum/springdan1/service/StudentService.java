package rs.ac.singidunum.springdan1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.springdan1.entity.Student;
import rs.ac.singidunum.springdan1.model.StudentModel;
import rs.ac.singidunum.springdan1.repository.StudentRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;

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
        Student student = new Student();
        student.setName(model.getName());
        student.setSurname(model.getSurname());
        student.setIndeks(model.getIndeks());
        student.setCreatedAt(LocalDateTime.now());
        return repository.save(student);
    }

    public Student updateStudent(Integer id, StudentModel model) {
        Student student = repository.findByIdAndDeletedAtIsNull(id).orElseThrow();
        student.setName(model.getName());
        student.setSurname(model.getSurname());
        student.setIndeks(model.getIndeks());
        student.setUpdatedAt(LocalDateTime.now());
        return repository.save(student);
    }

    public void deletedStudent(Integer id) {
        Student student = repository.findByIdAndDeletedAtIsNull(id).orElseThrow();
        student.setDeletedAt(LocalDateTime.now());
        repository.save(student);
    }
}
