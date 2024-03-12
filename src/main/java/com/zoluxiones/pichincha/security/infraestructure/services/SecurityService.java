package com.zoluxiones.pichincha.security.infraestructure.services;

import com.sun.jdi.InternalException;
import com.zoluxiones.pichincha.security.api.model.requests.LoginRequest;
import com.zoluxiones.pichincha.security.api.model.requests.RegisterUserRequest;
import com.zoluxiones.pichincha.security.api.model.responses.LogInResponse;
import com.zoluxiones.pichincha.security.core.entities.Role;
import com.zoluxiones.pichincha.security.core.entities.User;
import com.zoluxiones.pichincha.security.core.repositories.RolRepository;
import com.zoluxiones.pichincha.security.core.repositories.UserRepository;
import com.zoluxiones.pichincha.security.infraestructure.interfaces.ISecurityService;
import com.zoluxiones.pichincha.security.util.jwt.JwtTokenUtil;
import com.zoluxiones.pichincha.shared.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Service
@AllArgsConstructor
public class SecurityService implements ISecurityService {

    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder encoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final Validator validator;

    @Override
    public LogInResponse login(LoginRequest loginRequest) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateJwtToken(authentication);

        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new NotFoundException("User not found"));

        return new LogInResponse(jwt, user.getId());
    }

    @Override
    public String register(RegisterUserRequest registerUserRequest) {

        String rolNotFound = "Rol doesn't exist";

        Set<ConstraintViolation<RegisterUserRequest>> violations = validator.validate(registerUserRequest);

        if (!violations.isEmpty())
            throw new NotFoundException(violations.stream().map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", ")));

        if (Boolean.TRUE.equals(userRepository.existsByUsername(registerUserRequest.getUsername()))) {
            throw new NotFoundException("EL username ya esta en uso");
        }

        if (Boolean.TRUE.equals(userRepository.existsByEmail(registerUserRequest.getEmail()))) {
            throw new NotFoundException("El email ya esta en uso");
        }

        Role rol = rolRepository.findByName("USER");

        if (rol == null) {
            throw new NotFoundException(rolNotFound);
        }

        User user = User.builder()
                .username(registerUserRequest.getUsername())
                .names(registerUserRequest.getNames())
                .lastNames(registerUserRequest.getLastNames())
                .email(registerUserRequest.getEmail())
                .password(encoder.encode(registerUserRequest.getPassword()))
                .build();

        Set<Role> roles = new HashSet<>();
        roles.add(rol);

        user.setRoles(roles);

        if (Objects.equals(registerUserRequest.getUsername(), "admin")) {

            Role adminRol = rolRepository.findByName("ADMIN");

            if (adminRol == null) {
                throw new NotFoundException(rolNotFound);
            }

            roles.add(adminRol);
        }

        userRepository.save(user);

        return "Registro de usuario exitoso!";
    }

    @Override
    public String addRoleAdmin(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));

        Role rol = rolRepository.findByName("ADMIN");

        if (rol == null) {
            throw new NotFoundException("Rol doesn't exist");
        }

        user.getRoles().forEach(role -> {
            if (role.getName().equals("ADMIN")) {
                throw new NotFoundException("El usuario ya tiene el rol de admin");
            }
        });

        try {
            user.getRoles().add(rol);
            userRepository.save(user);

        } catch (Exception e) {
            throw new InternalException("Problem adding admin role");
        }


        return "Admin role added successfully!";
    }
}
