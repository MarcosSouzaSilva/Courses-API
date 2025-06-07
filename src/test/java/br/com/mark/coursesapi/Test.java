package br.com.mark.coursesapi;

import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidTokenException;
import br.com.mark.coursesapi.config.jwt.JwtUtils;

public class Test {

    public static void main(String[] args) throws InvalidTokenException {

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQWRtaW5pc3RyYWRvciIsImZ1bGxOYW1lIjoiSm_Do28gZGEgU2lsdmEiLCJlbWFpbCI6Im1hcmNvc2RzZnNzMmZ5ZEBleGFtcGxlLnJjb20iLCJlbmFibGVkIjp0cnVlLCJzdWIiOiIyNDkiLCJpYXQiOjE3NDU2MTE4MzAsImV4cCI6MTc0NTYxMjczMH0.MV2n14gTNMghAL1labYk3isT2-sC7YEflkyGTRNldzA";

        System.out.println(JwtUtils.isValidToken(token));
        System.out.println(JwtUtils.getRoleFromToken(token));

    }
}