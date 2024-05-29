package rs.ac.singidunum.springdan1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.springdan1.entity.StudyProgramme;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudyProgrammeRepository extends JpaRepository<StudyProgramme, Integer> {

    List<StudyProgramme> findAllByDeletedAtIsNull();

    Optional<StudyProgramme> findByIdAndDeletedAtIsNull(Integer id);
}
