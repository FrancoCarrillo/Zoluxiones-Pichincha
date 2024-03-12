package com.zoluxiones.pichincha.security.api.controller;

import com.zoluxiones.pichincha.security.api.model.requests.LoginRequest;
import com.zoluxiones.pichincha.security.api.model.requests.RegisterUserRequest;
import com.zoluxiones.pichincha.security.api.model.responses.LogInResponse;
import com.zoluxiones.pichincha.security.api.model.responses.MessageResponse;
import com.zoluxiones.pichincha.security.infraestructure.interfaces.ISecurityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@AllArgsConstructor
@Tag(name = "User")
public class UserController {

    private final ISecurityService securityService;

    @Operation(summary = "Login in system")
    @PostMapping("auth/login")
    public ResponseEntity<LogInResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(securityService.login(loginRequest));
    }
    @Operation(summary = "Register in system")
    @PostMapping("auth/register")
    public ResponseEntity<MessageResponse> register(@RequestBody RegisterUserRequest signUpRequest) {
        return ResponseEntity.ok(new MessageResponse(securityService.register(signUpRequest)));
    }

    @Operation(summary = "Add admin role to user", security = {@SecurityRequirement(name = "bearer-key")})
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add/{user_id}/admin")
    public ResponseEntity<String> addAdminRol(@PathVariable Long userId) {
        return ResponseEntity.ok(securityService.addRoleAdmin(userId));
    }
}
