package br.com.mark.coursesapi.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class Test {

    @Value("${jwt.secret:xD8vYh3zNq5wGm7KrBtUc9XlPaQsRfTe}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long accessTokenExpirationTimeMillis;

    @Value("${jwt.refresh-expiration}")
    private long refreshTokenExpirationTimeMillis;

    public Test(String jwtSecret, long accessTokenExpirationTimeMillis, long refreshTokenExpirationTimeMillis) {
        this.jwtSecret = jwtSecret;
        this.accessTokenExpirationTimeMillis = accessTokenExpirationTimeMillis;
        this.refreshTokenExpirationTimeMillis = refreshTokenExpirationTimeMillis;
    }



    public Test() {
    }


}
