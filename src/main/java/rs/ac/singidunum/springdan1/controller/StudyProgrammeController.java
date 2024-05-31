package rs.ac.singidunum.springdan1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.springdan1.entity.StudyProgramme;
import rs.ac.singidunum.springdan1.model.NameModel;
import rs.ac.singidunum.springdan1.service.StudyProgrammeService;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(path = "/api/study-programme")
public class StudyProgrammeController {

    private final StudyProgrammeService service;

    @GetMapping
    public List<StudyProgramme> getAll() {
        return service.getAllStudyProgrammes();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<StudyProgramme> getById(@PathVariable Integer id) {
        return ResponseEntity.of(service.getStudyProgrammeById(id));
    }

    @PostMapping
    public StudyProgramme create(@RequestBody NameModel model) {
        return service.saveStudyProgramme(model);
    }

    @PutMapping(path = "/{id}")
    public StudyProgramme update(@PathVariable Integer id, @RequestBody NameModel model) {
        return service.updateStudyProgramme(id, model);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.deleteStudyProgramme(id);
    }
}
