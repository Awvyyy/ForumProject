package com.example.repppp.services;

import com.example.repppp.PasswordEncoderrr;
import com.example.repppp.dto.request.RegisterUserRequest;
import com.example.repppp.dto.response.UserResponse;
import com.example.repppp.mappers.UserMapper;
import com.example.repppp.models.AppUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppUserService {

    private final UserMapper userMapper;
    private final PasswordEncoderrr passwordEncoderrr;

    public AppUserService(UserMapper userMapper, PasswordEncoderrr passwordEncoderrr){
        this.userMapper = userMapper;
        this.passwordEncoderrr = passwordEncoderrr;
    }

    private void validateRegisterRequest(RegisterUserRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request body is required");
        }

        if (request.getUsername() == null || request.getUsername().isBlank()) {
            throw new IllegalArgumentException("Username is required");
        }

        if (request.getUsername().length() < 3 || request.getUsername().length() > 50) {
            throw new IllegalArgumentException("Username must be between 3 and 50 characters");
        }

        if (request.getPassword() == null || request.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password is required");
        }

        if (request.getPassword().length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }
    }

    private UserResponse toResponse(AppUser appUser){
        return new UserResponse(appUser.getId(),
                appUser.getUsername(),
                appUser.getReputation());
    }

    public UserResponse findUserById(Long id){
        AppUser user = userMapper.findById(id);
        if (user == null){
            throw new RuntimeException("User not found,id: " + id);
        }

        return toResponse(user);
    }

    public UserResponse findUserByUsername(String username){
        AppUser user = userMapper.findByUsername(username);
        if (user == null){
            throw new RuntimeException("User not found, username: " + username);
        }
        return toResponse(user);
    }

    @Transactional
    public UserResponse register (RegisterUserRequest request){
        validateRegisterRequest(request);

        AppUser existingUser = userMapper.findByUsername(request.getUsername());

        if (existingUser != null){
            throw new RuntimeException("Username already exists");
        }

        String passwordHash = passwordEncoderrr.passwordEncoder().encode(request.getPassword());

        AppUser user = new AppUser();
        user.setUsername(request.getUsername());
        user.setPasswordHash(passwordHash);
        user.setReputation(0);

        userMapper.insert(user);

        return toResponse(user);
    }

    @Transactional
    public UserResponse updateReputation (Long id, int reputation){
        int updated = userMapper.updateReputation(id, reputation);

        if (updated == 0){
            throw new RuntimeException("User not found, id: " + id);
        }

        AppUser updatedUser = userMapper.findById(id);
        return toResponse(updatedUser);
    }

    public boolean checkPassword (String username, String password){
        AppUser user = userMapper.findByUsername(username);
        if (user == null){
            return false;
        }

        return passwordEncoderrr.passwordEncoder().matches(password, user.getPasswordHash());
    }
}
