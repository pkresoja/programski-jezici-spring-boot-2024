package rs.ac.singidunum.springdan1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.springdan1.entity.StudyProgramme;
import rs.ac.singidunum.springdan1.model.StudyProgrammeModel;
import rs.ac.singidunum.springdan1.repository.StudyProgrammeRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudyProgrammeService {

    private final StudyProgrammeRepository repository;

    public List<StudyProgramme> getAllStudyProgrammes() {
        return repository.findAllByDeletedAtIsNull();
    }

    public Optional<StudyProgramme> getStudyProgrammeById(Integer id) {
        return repository.findByIdAndDeletedAtIsNull(id);
    }

    public StudyProgramme saveStudyProgramme(StudyProgrammeModel model) {
        StudyProgramme programme = new StudyProgramme();
        programme.setName(model.getName());
        programme.setCreatedAt(LocalDateTime.now());
        return repository.save(programme);
    }

    public StudyProgramme updateStudyProgramme(Integer id, StudyProgrammeModel model) {
        StudyProgramme programme = repository.findByIdAndDeletedAtIsNull(id).orElseThrow();
        programme.setName(model.getName());
        programme.setUpdatedAt(LocalDateTime.now());
        return repository.save(programme);
    }

    public void deleteStudyProgramme(Integer id) {
        StudyProgramme programme = repository.findByIdAndDeletedAtIsNull(id).orElseThrow();
        programme.setDeletedAt(LocalDateTime.now());
        repository.save(programme);
    }
}
