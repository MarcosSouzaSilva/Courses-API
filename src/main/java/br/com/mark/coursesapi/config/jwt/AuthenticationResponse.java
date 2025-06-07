package br.com.mark.coursesapi.config.jwt;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Data
@Component
public class AuthenticationResponse {

    private String accessToken;

    @Value("${jwt.expiration}")
    private Long accessExpiresIn;

    private String refreshToken;

    @Value("${jwt.refresh-expiration}")
    private Long refreshExpiresIn;

    public static Long REFRESH_EXPIRE;
    public static Long TOKEN_EXPIRE;

    @PostConstruct
    public void init () {
        REFRESH_EXPIRE = this.refreshExpiresIn;
        TOKEN_EXPIRE = this.accessExpiresIn;
    }



    public AuthenticationResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessExpiresIn = TOKEN_EXPIRE;
        this.refreshExpiresIn = REFRESH_EXPIRE;
    }

    public AuthenticationResponse(String refreshToken) {
        this.refreshToken = refreshToken;
        this.refreshExpiresIn = REFRESH_EXPIRE;
    }


}