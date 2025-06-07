package br.com.mark.coursesapi.config.jwt;

import br.com.mark.coursesapi.dataprovider.entity.Teachers;
import br.com.mark.coursesapi.dataprovider.entity.User;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidTokenException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@AllArgsConstructor
@NoArgsConstructor
@Component
public class JwtUtils {


    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long accessTokenExpirationTimeMillis;

    @Value("${jwt.refresh-expiration}")
    private long refreshTokenExpirationTimeMillis;


    public static String JWT_SECRET_STATIC;
    public static long ACCESS_TOKEN_EXPIRATION_TIME_STATIC;
    public static long REFRESH_TOKEN_EXPIRATION_TIME_STATIC;
    public static Key SIGNING_KEY_STATIC;

    @PostConstruct
    public void init() {
        JWT_SECRET_STATIC = this.jwtSecret;
        ACCESS_TOKEN_EXPIRATION_TIME_STATIC = this.accessTokenExpirationTimeMillis;
        REFRESH_TOKEN_EXPIRATION_TIME_STATIC = this.refreshTokenExpirationTimeMillis;
        SIGNING_KEY_STATIC = Keys.hmacShaKeyFor(JWT_SECRET_STATIC.getBytes(StandardCharsets.UTF_8));
    }

    public static String generateAccessTokenForUser(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());
        claims.put("fullName", user.getFullName());
        claims.put("enabled", user.getEnabled());
        claims.put("role", user.getRole().getRole());

        return Jwts.builder().setClaims(claims).setSubject(user.getId().toString()).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME_STATIC)).signWith(SignatureAlgorithm.HS256, SIGNING_KEY_STATIC).compact();
    }

    public static String generateExternalAccessTokenForUser(User user, Long id) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());

        return Jwts.builder().setClaims(claims).setSubject(id.toString()).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME_STATIC)).signWith(SignatureAlgorithm.HS256, SIGNING_KEY_STATIC).compact();
    }

    public static String generateAccessTokenForTeacher(Teachers teachers) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", teachers.getEmail());
        claims.put("fullName", teachers.getFullName());
        claims.put("courses", teachers.getCourses());
        claims.put("enabled", teachers.getEnabled());
        claims.put("role", teachers.getRole().getRole());

        return Jwts.builder().setClaims(claims).setSubject(teachers.getId().toString()).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME_STATIC)).signWith(SignatureAlgorithm.HS256, SIGNING_KEY_STATIC).compact();
    }

    public static String generateRefreshTokenForUser(User user) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("email", user.getEmail());
        claims.put("fullName", user.getFullName());
        claims.put("enabled", user.getEnabled());
        claims.put("role", user.getRole().getRole());

        return Jwts.builder().setClaims(claims).setSubject(user.getId().toString()).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME_STATIC)).signWith(SignatureAlgorithm.HS256, SIGNING_KEY_STATIC).compact();
    }

    public static String generateExternalRefreshTokenForUser(User user, Long id) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());

        return Jwts.builder().setClaims(claims).setSubject(id.toString()).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME_STATIC)).signWith(SignatureAlgorithm.HS256, SIGNING_KEY_STATIC).compact();
    }

    public static String generateRefreshTokenForTeacher(Teachers teachers) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("email", teachers.getEmail());
        claims.put("fullName", teachers.getFullName());
        claims.put("courses", teachers.getCourses());
        claims.put("enabled", teachers.getEnabled());
        claims.put("role", teachers.getRole().getRole());

        return Jwts.builder().setClaims(claims).setSubject(teachers.getId().toString()).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME_STATIC)).signWith(SignatureAlgorithm.HS256, SIGNING_KEY_STATIC).compact();
    }

    public static boolean isValidToken(String token) throws InvalidTokenException {
        try {
            Jwts.parserBuilder().setSigningKey(SIGNING_KEY_STATIC).build().parse(token);
            return true;
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }

    public static String getRoleFromToken(String bearerToken) throws InvalidTokenException {
        String token = bearerToken.substring(7).trim();

        var claim = Jwts.parserBuilder().setSigningKey(SIGNING_KEY_STATIC).build().parseClaimsJws(token).getBody();

        isValidToken(token);

        String role = claim.get("role", String.class);

        return role;
    }

    public static Long getIdFromBearerToken(String bearerToken) throws InvalidTokenException {

        String token = bearerToken.substring(7).trim();

        var claim = Jwts.parserBuilder().setSigningKey(SIGNING_KEY_STATIC).build().parseClaimsJws(token).getBody();

        isValidToken(token);

        String id = claim.get("sub", String.class);

        return Long.parseLong(id);
    }

    public static Long getIdFromToken(String token) throws InvalidTokenException {

        var claim = Jwts.parserBuilder().setSigningKey(SIGNING_KEY_STATIC).build().parseClaimsJws(token).getBody();

        isValidToken(token);

        String id = claim.get("sub", String.class);

        return Long.parseLong(id);
    }

    public static String getEmailFromToken(String bearerToken) throws InvalidTokenException {
        String token = bearerToken.substring(7).trim();

        var claim = Jwts.parserBuilder().setSigningKey(SIGNING_KEY_STATIC).build().parseClaimsJws(token).getBody();

        isValidToken(token);

        String email = claim.get("email", String.class);

        return email;
    }

}