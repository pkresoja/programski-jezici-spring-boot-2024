package rs.ac.singidunum.springdan1.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.singidunum.springdan1.entity.Course;

@NoArgsConstructor
@Getter
@Setter
public class ToggleCourse {
    private Boolean active;
    private Course course;
}
