package rs.ac.singidunum.springdan1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.springdan1.entity.Course;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findAllByDeletedAtIsNull();

    Optional<Course> findByIdAndDeletedAtIsNull(Integer id);
}
