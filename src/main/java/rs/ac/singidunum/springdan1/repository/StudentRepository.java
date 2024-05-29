package rs.ac.singidunum.springdan1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.springdan1.entity.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findAllByDeletedAtIsNull();

    Optional<Student> findByIdAndDeletedAtIsNull(Integer id);

    List<Student> findByIndeksContainsAndDeletedAtIsNull(String indeks);
}
