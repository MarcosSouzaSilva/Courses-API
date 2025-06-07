package br.com.mark.coursesapi.dataprovider.implementation;

import br.com.mark.coursesapi.dataprovider.entity.Teachers;
import br.com.mark.coursesapi.dataprovider.entity.User;
import br.com.mark.coursesapi.dataprovider.mapper.TeacherDataProviderMapper;
import br.com.mark.coursesapi.dataprovider.repository.TeachersRepository;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.DuplicateContactException;
import br.com.mark.coursesapi.usecases.domain.TeacherDomain;
import br.com.mark.coursesapi.usecases.gateway.TeacherGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TeacherDataProvider implements TeacherGateway {

    private final TeachersRepository teachersRepository;


    @Override
    public Teachers save (TeacherDomain teacherDomainUseCase) throws DuplicateContactException {

        var teacherToEntity = TeacherDataProviderMapper.convert(teacherDomainUseCase);

        if (teachersRepository.findByEmail(teacherToEntity.getEmail()).isPresent() || teachersRepository.findByPhone(teacherToEntity.getPhone()).isPresent()) throw new DuplicateContactException();

        return teachersRepository.save(teacherToEntity);
    }
    @Override
    public Teachers refreshToken(Long id) throws Exception {


        var idUser = teachersRepository.findById(id);

        if (idUser.isEmpty()) throw new Exception();

        if (idUser.get().getId() == null) throw new Exception();


        return idUser.get();
    }



}