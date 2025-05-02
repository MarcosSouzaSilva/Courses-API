package br.com.mark.coursesapi.dataprovider.mapper;

import br.com.mark.coursesapi.dataprovider.entity.User;
import br.com.mark.coursesapi.usecases.domain.UserDomain;
import br.com.mark.coursesapi.usecases.domain.enums.Role;
import java.time.LocalDateTime;


public class UserDataProviderMapper {


    public static User convert(UserDomain domain) {
        return User.builder()
                .fullName(domain.getFullName())
                .email(domain.getEmail())
                .password(domain.getPassword())
                .birthDate(domain.getBirthDate())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .enabled(true)
                .phone(domain.getPhone())
                .role(Role.ADMIN)
                .build();
    }

}