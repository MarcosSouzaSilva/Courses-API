package br.com.mark.coursesapi.dataprovider.implementation;

import br.com.mark.coursesapi.dataprovider.entity.Teachers;
import br.com.mark.coursesapi.dataprovider.mapper.TeacherDataProviderMapper;
import br.com.mark.coursesapi.dataprovider.repository.TeachersRepository;
import br.com.mark.coursesapi.usecases.domain.TeacherDomain;
import br.com.mark.coursesapi.usecases.gateway.TeacherGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TeacherDataProvider implements TeacherGateway {

    private final TeachersRepository teachersRepository;


    @Override
    public Teachers save (TeacherDomain teacherDomainUseCase){

        var teacherToEntity = TeacherDataProviderMapper.convert(teacherDomainUseCase);

        return teachersRepository.save(teacherToEntity);
    }

}