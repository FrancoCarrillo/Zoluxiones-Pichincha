package com.zoluxiones.pichincha.security.infraestructure.interfaces;

import com.zoluxiones.pichincha.security.api.model.request.CreateUserRequest;
import com.zoluxiones.pichincha.security.api.model.request.LoginRequest;
import com.zoluxiones.pichincha.security.api.model.response.LoginResponse;
import com.zoluxiones.pichincha.security.core.entities.UserAccount;
import reactor.core.publisher.Mono;

public interface IUserService {
    Mono<LoginResponse> login(LoginRequest request);
    Mono<UserAccount> createUser(CreateUserRequest request);
}
