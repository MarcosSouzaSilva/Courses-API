package br.com.mark.coursesapi.usecases.gateway;

import br.com.mark.coursesapi.dataprovider.entity.Teachers;
import br.com.mark.coursesapi.usecases.domain.TeacherDomain;

public interface TeacherGateway {

    Teachers save(TeacherDomain teacherDomainUseCase);

}