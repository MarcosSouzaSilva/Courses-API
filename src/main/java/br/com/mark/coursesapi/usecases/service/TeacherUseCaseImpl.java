package br.com.mark.coursesapi.usecases.service;

import br.com.mark.coursesapi.config.jwt.AuthenticationResponse;
import br.com.mark.coursesapi.config.jwt.JwtUtils;
import br.com.mark.coursesapi.usecases.interfaces.TeacherUseCase;
import br.com.mark.coursesapi.usecases.validation.TeacherLoginValidator;
import br.com.mark.coursesapi.usecases.domain.TeacherDomain;
import br.com.mark.coursesapi.usecases.gateway.TeacherGateway;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TeacherUseCaseImpl implements TeacherUseCase {


    @Autowired
    private TeacherGateway teacherGateway;

    private final TeacherLoginValidator teacherLoginValidator;

    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> saveTeacher(TeacherDomain teachersDomain) throws Exception {

        var validator = teacherLoginValidator.saveTeacherValidator(teachersDomain);

       /* if (teachersRepository.findByEmail(teacherUseCase.getEmail()).isPresent() || teachersRepository.findByPhone(teacherUseCase.getPhone()).isPresent()) throw new DuplicateContactException();*/

        validator.setPassword(passwordEncoder.encode(validator.getPassword()));

        var teacher = teacherGateway.save(validator);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse(JwtUtils.generateTokenForTeachers(teacher),
                JwtUtils.generateRefreshTokenForTeachers(teacher), null);

        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationResponse);

    }

}