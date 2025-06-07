package br.com.mark.coursesapi.usecases.service;

import br.com.mark.coursesapi.config.jwt.AuthenticationResponse;
import br.com.mark.coursesapi.config.jwt.JwtUtils;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.DuplicateContactException;
import br.com.mark.coursesapi.usecases.interfaces.TeacherUseCase;
import br.com.mark.coursesapi.usecases.validation.TeacherLoginValidator;
import br.com.mark.coursesapi.usecases.domain.TeacherDomain;
import br.com.mark.coursesapi.usecases.gateway.TeacherGateway;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TeacherUseCaseImpl implements TeacherUseCase {

    private TeacherGateway teacherGateway;

    private final TeacherLoginValidator teacherLoginValidator;

    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> saveTeacher(TeacherDomain teachersDomain) throws Exception {

        teacherLoginValidator.teacherValidator(teachersDomain);


        teachersDomain.setPassword(passwordEncoder.encode(teachersDomain.getPassword()));

        var teacher = teacherGateway.save(teachersDomain);

        AuthenticationResponse authenticationResponse = new AuthenticationResponse(JwtUtils.generateAccessTokenForTeacher(teacher),
                JwtUtils.generateRefreshTokenForTeacher(teacher));

        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationResponse);

    }

}