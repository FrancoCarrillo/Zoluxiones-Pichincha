package com.zoluxiones.pichincha.security.infraestructure.interfaces;


import com.zoluxiones.pichincha.security.api.model.requests.LoginRequest;
import com.zoluxiones.pichincha.security.api.model.requests.RegisterUserRequest;
import com.zoluxiones.pichincha.security.api.model.responses.LogInResponse;

public interface ISecurityService {

    LogInResponse login(LoginRequest loginRequest);

    String register(RegisterUserRequest registerUserRequest);

    String addRoleAdmin(Long user_id);
}
