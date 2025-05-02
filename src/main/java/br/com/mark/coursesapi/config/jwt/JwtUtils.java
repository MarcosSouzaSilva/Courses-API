package br.com.mark.coursesapi.config.jwt;

import br.com.mark.coursesapi.dataprovider.entity.Teachers;
import br.com.mark.coursesapi.dataprovider.entity.User;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidTokenException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtUtils {

    //mudar pra variavel de ambiente
    private static final String SECRET = "xD8vYh3zNq5wGm7KxBtUc9XlPaQsRfTe";

    private static final Key SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    private static final long EXPIRATION_TIME_IN_MINUTES = 900_000L;

    private static final long EXPIRATION_TIME_REFRESH_TOKEN = 864000000;


    public static String generateTokenForUser(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());
        claims.put("fullName", user.getFullName());
        claims.put("enabled", user.getEnabled());
        claims.put("role", user.getRole().getRole());

        return Jwts.builder().setClaims(claims).setSubject(user.getId().toString()).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_IN_MINUTES)).signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public static String generateTokenForUserOut(User user, Long id) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());

        return Jwts.builder().setClaims(claims).setSubject(id.toString()).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_IN_MINUTES)).signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public static String generateTokenForTeachers(Teachers teachers) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", teachers.getEmail());
        claims.put("fullName", teachers.getFullName());
        claims.put("courses", teachers.getCourses());
        claims.put("enabled", teachers.getEnabled());
        claims.put("role", teachers.getRole().getRole());

        return Jwts.builder().setClaims(claims).setSubject(teachers.getId().toString()).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_IN_MINUTES)).signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public static String generateRefreshTokenForUser(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());
        claims.put("fullName", user.getFullName());
        claims.put("enabled", user.getEnabled());
        claims.put("role", user.getRole().getRole());

        return Jwts.builder().setClaims(claims).setSubject(user.getId().toString()).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_REFRESH_TOKEN)).signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public static String generateRefreshTokenForUserOut(User user, Long id) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());

        return Jwts.builder().setClaims(claims).setSubject(id.toString()).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_REFRESH_TOKEN)).signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public static String generateRefreshTokenForTeachers(Teachers teachers) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", teachers.getEmail());
        claims.put("fullName", teachers.getFullName());
        claims.put("courses", teachers.getCourses());
        claims.put("enabled", teachers.getEnabled());
        claims.put("role", teachers.getRole().getRole());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(teachers.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_REFRESH_TOKEN))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public static boolean validateToken(String token) throws InvalidTokenException {
        try {
            Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }

    public static String getRoleForToken(String token) {
        var claim = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();

        String role = claim.get("role", String.class);

        return role;

    }

    public static Long getIdForToken(String token) {
        var claim = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();

        String id = claim.get("sub", String.class);


        return Long.parseLong(id);
    }

    public static String getEmailForToken(String token) {
        var claim = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();

        String email = claim.get("email", String.class);

        return email;

    }

}