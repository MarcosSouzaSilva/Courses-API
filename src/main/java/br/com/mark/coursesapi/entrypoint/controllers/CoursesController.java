package br.com.mark.coursesapi.entrypoint.controllers;

import br.com.mark.coursesapi.config.Test;
import br.com.mark.coursesapi.dataprovider.entity.User;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.AccessDeniedException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.CoursesCreationException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidTokenException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.UserNotFoundException;
import br.com.mark.coursesapi.entrypoint.mapper.request.CoursesEntryPointRequestMapper;
import br.com.mark.coursesapi.entrypoint.mapper.request.UserEntryPointRequestMapper;
import br.com.mark.coursesapi.entrypoint.model.request.CourseModelRequest;
import br.com.mark.coursesapi.entrypoint.model.request.UserModelRequest;
import br.com.mark.coursesapi.entrypoint.mapper.request.TeacherEntryPointRequestMapper;
import br.com.mark.coursesapi.entrypoint.model.request.TeacherModelRequest;
import br.com.mark.coursesapi.entrypoint.model.request.UserOutModelRequest;
import br.com.mark.coursesapi.usecases.interfaces.CoursesUseCase;
import br.com.mark.coursesapi.usecases.interfaces.TeacherUseCase;
import br.com.mark.coursesapi.usecases.interfaces.UserUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/courses")
@AllArgsConstructor
public class CoursesController {

    private final TeacherUseCase teacherUseCaseInterface;

    private final UserUseCase userUseCaseInterface;

    private final CoursesUseCase coursesUseCaseInterface;

    @PostMapping("/signIn")
    public ResponseEntity<?> signInUser(@Valid @RequestBody UserModelRequest userModelRequest) throws Exception {
        var user = UserEntryPointRequestMapper.convert(userModelRequest);

        return userUseCaseInterface.saveUser(user);
    }

    @GetMapping("/{id}/{token}")
    public ResponseEntity<?> getById(@PathVariable Long id, @PathVariable("token") String token) throws Exception {
        return userUseCaseInterface.getById(id, token);
    }

    @PostMapping("/teachers")
    public ResponseEntity<?> signInTeachers(@Valid @RequestBody TeacherModelRequest teacherModelRequest) throws Exception {
        var teacher = TeacherEntryPointRequestMapper.convert(teacherModelRequest);

        return teacherUseCaseInterface.saveTeacher(teacher);
    }

    @GetMapping("/allStudent")
    public ResponseEntity<Page<User>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestHeader("Authorization") String token) throws InvalidTokenException, AccessDeniedException {
        return userUseCaseInterface.getAll(page, size, token);
    }

    @GetMapping("/auth/refresh/{token}")
    public ResponseEntity<?> refreshToken(@PathVariable String token) throws Exception {
        return userUseCaseInterface.refreshToken(token);
    }

    @PostMapping("/signOut")
    public ResponseEntity<?> signOutUser(@Valid @RequestBody UserOutModelRequest userDomain) throws Exception {
        var user = UserEntryPointRequestMapper.convertUserOutToEntity(userDomain);

        return userUseCaseInterface.signOutUser(user);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@ModelAttribute CourseModelRequest request, @RequestHeader("Authorization") String token) throws UserNotFoundException, AccessDeniedException, IOException, InvalidTokenException, CoursesCreationException {
        var course = CoursesEntryPointRequestMapper.convert(request);

        return coursesUseCaseInterface.saveCourses(course, token);
    }




}