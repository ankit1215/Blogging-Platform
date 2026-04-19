package com.blogging.service;

import com.blogging.dto.LoginRequest;
import com.blogging.dto.RegisterRequest;
import com.blogging.entity.Role;
import com.blogging.entity.User;
import com.blogging.repository.UserRepository;
import com.blogging.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public String register(RegisterRequest req) {

        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(encoder.encode(req.getPassword()));
        user.setEmail(req.getEmail());
        user.setFullName(req.getFullName());
        user.setRole(Role.ROLE_USER);

        userRepository.save(user);

        return "User Registered";
    }

    public Map<String, Object> login(LoginRequest req) {

    User user = userRepository.findByUsername(req.getUsername())
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (!encoder.matches(req.getPassword(), user.getPassword())) {
        throw new RuntimeException("Invalid password");
    }

        String token = jwtUtil.generateToken(user);


        Map<String, Object> response = new HashMap<>();
    response.put("token", token);
    response.put("username", user.getUsername());
    response.put("role", user.getRole());

    return response;
}
}
