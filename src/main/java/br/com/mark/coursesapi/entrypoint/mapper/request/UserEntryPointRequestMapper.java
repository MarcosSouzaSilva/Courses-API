package br.com.mark.coursesapi.entrypoint.mapper.request;

import br.com.mark.coursesapi.entrypoint.model.request.UserModelRequest;
import br.com.mark.coursesapi.entrypoint.model.request.UserOutModelRequest;
import br.com.mark.coursesapi.usecases.domain.UserDomain;
import br.com.mark.coursesapi.usecases.domain.UserOutDomain;
import br.com.mark.coursesapi.usecases.domain.enums.Role;
import java.time.LocalDateTime;

public class UserEntryPointRequestMapper {

    public static UserDomain convert(UserModelRequest userModelRequest) {

        return UserDomain.builder()
                .fullName(userModelRequest.getFullName())
                .email(userModelRequest.getEmail())
                .password(userModelRequest.getPassword())
                .birthDate(userModelRequest.getBirthDate())
                .enabled(true)
                .createAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .phone(userModelRequest.getPhone())
                .role(Role.ADMIN)
                .build();
    }

    public static UserOutDomain convertUserOutToEntity(UserOutModelRequest userDomain) {
        return UserOutDomain.builder()
                .email(userDomain.getEmail())
                .password(userDomain.getPassword())
                .build();
    }

}