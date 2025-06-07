package br.com.mark.coursesapi.endpoints.controller;

import br.com.mark.coursesapi.config.jwt.AuthenticationResponse;
import br.com.mark.coursesapi.config.jwt.JwtUtils;
import br.com.mark.coursesapi.config.security.ConfigPathSecurity;
import br.com.mark.coursesapi.dataprovider.mapper.UserDataProviderMapper;
import br.com.mark.coursesapi.entrypoint.controllers.CoursesController;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidEmailException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidPasswordException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.InvalidPhoneNumberException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.UserCreationException;
import br.com.mark.coursesapi.entrypoint.handler.exceptions.UserNotFoundException;
import br.com.mark.coursesapi.entrypoint.mapper.request.UserEntryPointRequestMapper;
import br.com.mark.coursesapi.unittests.UserEntityTest;
import br.com.mark.coursesapi.usecases.domain.UserDomain;
import br.com.mark.coursesapi.usecases.gateway.UserGateway;
import br.com.mark.coursesapi.usecases.interfaces.CoursesUseCase;
import br.com.mark.coursesapi.usecases.interfaces.TeacherUseCase;
import br.com.mark.coursesapi.usecases.interfaces.UserUseCase;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.client.HttpClientErrorException;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = CoursesController.class)
@Import(ConfigPathSecurity.class) // se quiser carregar a config real
@AutoConfigureMockMvc(addFilters = false) // desativa os filtros de segurança no teste

public class ControllerTest {

    static String COURSES_API = "/api/v1/courses";

    @Autowired
    MockMvc mvc;

    @MockitoBean
    UserUseCase userUseCase;

    @MockitoBean
    UserGateway userGateway;

    @MockitoBean
    TeacherUseCase teacherUseCase;

    @MockitoBean
    CoursesUseCase coursesUseCase;

    @Test
    @DisplayName("Deve criar um usuário com sucesso")
    public void shouldCreateUserSuccessfully() throws Exception {
        var request = UserEntityTest.createUser();

        AuthenticationResponse mockAuthResponse = new AuthenticationResponse(
                "accessToken",
                "refreshToken"
        );

        BDDMockito.given(userUseCase.saveUser(any(UserDomain.class)))
                .willReturn(ResponseEntity.status(HttpStatus.CREATED).body(mockAuthResponse));

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule());

        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);


        String json = objectMapper.writeValueAsString(request);

        MockHttpServletRequestBuilder requestBuilder = post(COURSES_API + "/signIn")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(requestBuilder)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("accessToken").value(Matchers.startsWith("accessToken")))
                .andExpect(jsonPath("refreshToken").value(Matchers.startsWith("refreshToken")))
                .andExpect(jsonPath("refreshToken").isString())
                .andExpect(jsonPath("accessToken").isString())
                .andExpect(jsonPath("expiresIn").isNotEmpty());
    }

    //@Test
    @DisplayName("Deve lançar exceção quando o id no endpoint getById nao existir")
    public void shouldThrowExceptionForInvalidId() throws Exception {

        Long invalidId = 12L; // No log aparece 152L, qual está correto?
        String anyToken = "any_valid_token";

        Mockito.doThrow(new UserNotFoundException())
                .when(userUseCase).getById(Mockito.eq(invalidId), any(String.class));

        MockHttpServletRequestBuilder requestBuilder = get(COURSES_API + "/" + invalidId)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + anyToken);

        mvc.perform(requestBuilder)
                .andExpect(status().isNotFound())
                .andExpect(result -> assertInstanceOf(UserNotFoundException.class, result.getResolvedException()));

        Mockito.verify(userUseCase, Mockito.times(1)).getById(Mockito.eq(invalidId), any(String.class));
        Mockito.verify(userGateway, Mockito.times(1)).getById(Mockito.eq(invalidId)); // Linha 138
    }


    @Test
    @DisplayName("Deve lançar exceção quando ocorrer um erro ao cadastrar um usuário invalido")
    public void shouldThrowExceptionForInvalidUser() throws Exception {
        var request = UserEntityTest.createUserWithInvalidEmail();

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule());

        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String json = objectMapper.writeValueAsString(request);

        BDDMockito.given(userUseCase.saveUser(any(UserDomain.class)))
                .willThrow(UserCreationException.class);

        MvcResult result = mvc.perform(post("/api/v1/courses/signIn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andReturn();

        String responseJson = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(responseJson);

        String message = root.get("message").asText();
        String error = root.get("error").asText();

        assertEquals("Error creating your account. Check the information and try again.", message);
        assertEquals("UserCreationException", error);
    }


    @Test
    @DisplayName("Deve lançar exceção ao receber e-mail inválido")
    public void shouldThrowExceptionForInvalidEmail() throws Exception {
        var request = UserEntityTest.createUserWithInvalidEmail();

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule());

        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String json = objectMapper.writeValueAsString(request);

        BDDMockito.given(userUseCase.saveUser(any(UserDomain.class)))
                .willThrow(InvalidEmailException.class);

        MvcResult result = mvc.perform(post("/api/v1/courses/signIn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andReturn();

        String responseJson = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(responseJson);

        String message = root.get("message").asText();
        String error = root.get("error").asText();

        assertEquals("Please enter a valid email address. It should contain a username, the '@' symbol, and a valid domain (e.g., example@domain.com).", message);
        assertEquals("InvalidEmailException", error);
    }

    @Test
    @DisplayName("Deve lançar exceção ao receber senha inválida")
    public void shouldThrowExceptionForInvalidPassword() throws Exception {
        var request = UserEntityTest.createUserWithInvalidPassword();

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule());

        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String json = objectMapper.writeValueAsString(request);

        BDDMockito.given(userUseCase.saveUser(any(UserDomain.class)))
                .willThrow(InvalidPasswordException.class);

        MvcResult result = mvc.perform(post("/api/v1/courses/signIn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andReturn();

        String responseJson = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(responseJson);

        String message = root.get("message").asText();
        String error = root.get("error").asText();

        assertEquals("Password must be at least 8 characters long and include at least one uppercase letter, one lowercase letter, one number, and one special character (@$!%*?&).", message);
        assertEquals("InvalidPasswordException", error);
    }

    @Test
    @DisplayName("Deve lançar exceção ao receber número de telefone inválido")
    public void shouldThrowExceptionForInvalidPhoneNumber() throws Exception {
        var request = UserEntityTest.createUserWithInvalidPhone();

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule());

        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String json = objectMapper.writeValueAsString(request);

        BDDMockito.given(userUseCase.saveUser(any(UserDomain.class)))
                .willThrow(InvalidPhoneNumberException.class);

        MvcResult result = mvc.perform(post("/api/v1/courses/signIn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andReturn();

        String responseJson = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(responseJson);

        String message = root.get("message").asText();
        String error = root.get("error").asText();

        assertEquals("Please enter a valid phone number. Expected format: (XX)XXXXX-XXXX or similar. Make sure to include the area code and a 9- or 8-digit number.", message);
        assertEquals("InvalidPhoneNumberException", error);
    }


    //@Test
    @DisplayName("Deve imprimir accessToken e refreshToken corretamente") // arrumando o teste ainda
    public void shouldPrintTokensAfterLogin() throws Exception {
        var request = UserEntityTest.createUser();
        var domain = UserEntryPointRequestMapper.convert(request);
        var user = UserDataProviderMapper.convert(domain);
        user.setId(1L);

        String accessToken = JwtUtils.generateAccessTokenForUser(user);
        String refreshToken = JwtUtils.generateRefreshTokenForUser(user);

        AuthenticationResponse mockAuthResponse = new AuthenticationResponse(
                accessToken,
                refreshToken
        );


        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String json = objectMapper.writeValueAsString(request);

        MvcResult result = mvc.perform(post("/api/v1/courses/signIn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk()) // ou isCreated(), conforme o seu controller
                .andReturn();

        String responseJson = result.getResponse().getContentAsString();
        System.err.println(JwtUtils.generateRefreshTokenForUser(user));
        // Aqui extraímos os campos do JSON
        JsonNode root = objectMapper.readTree(responseJson);
        String accessTokens = root.get("accessToken").asText();
        String refreshTokens = root.get("refreshToken").asText();

        // Imprime no console
        System.out.println("Access Token: " + accessToken);
        System.out.println("Refresh Token: " + refreshToken);

        // Verifica se estão vindo corretamente
        assertNotNull(accessTokens);
        assertNotNull(refreshTokens);
        assertTrue(accessToken.startsWith("ey"));
        assertTrue(refreshToken.startsWith("ey"));
    }


}