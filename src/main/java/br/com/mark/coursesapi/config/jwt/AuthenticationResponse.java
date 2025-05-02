package br.com.mark.coursesapi.config.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthenticationResponse {

    private String accessToken;

    private String refreshToken;

    private Long expiresIn;




}
