package com.zoluxiones.pichincha.security.api.handler;

import com.zoluxiones.pichincha.security.api.model.request.CreateUserRequest;
import com.zoluxiones.pichincha.security.api.model.request.LoginRequest;
import com.zoluxiones.pichincha.security.api.model.response.LoginResponse;
import com.zoluxiones.pichincha.security.core.entities.UserAccount;
import com.zoluxiones.pichincha.security.infraestructure.services.UserService;
import com.zoluxiones.pichincha.shared.validation.ObjectValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserHandler  {

    private final UserService userService;
    private final ObjectValidator objectValidator;

    public Mono<ServerResponse> login(ServerRequest request) {
        Mono<LoginRequest> loginRequest = request.bodyToMono(LoginRequest.class)
                .doOnNext(objectValidator::validate);

        return loginRequest
                .flatMap(res -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(userService.login(res), LoginResponse.class));
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        Mono<CreateUserRequest> loginRequest = request.bodyToMono(CreateUserRequest.class)
                .doOnNext(objectValidator::validate);

        return loginRequest
                .flatMap(res -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(userService.createUser(res), UserAccount.class));
    }

}
