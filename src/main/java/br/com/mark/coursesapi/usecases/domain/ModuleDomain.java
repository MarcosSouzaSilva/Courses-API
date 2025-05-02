package br.com.mark.coursesapi.usecases.domain;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ModuleDomain {



    String title;

    Integer order;

    @OneToMany
    private LessonDomain lessons;

    @ManyToOne
    private List<CoursesDomain> cours;


}
