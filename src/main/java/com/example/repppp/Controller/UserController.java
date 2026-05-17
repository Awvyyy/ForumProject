package com.example.repppp.Controller;

import com.example.repppp.dto.request.RegisterUserRequest;
import com.example.repppp.dto.response.UserResponse;
import com.example.repppp.services.AppUserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final AppUserService appUserService;

    public UserController(AppUserService appUserService){
        this.appUserService = appUserService;
    }

    @PostMapping("/register")
    public UserResponse register (@RequestBody RegisterUserRequest request){
        return appUserService.register(request);
    }

    @GetMapping("/id/{id}")
    public UserResponse getUserById(@PathVariable Long id){
        return appUserService.findUserById(id);
    }

    @GetMapping("/username/{username}")
    public UserResponse getUserByUsername(@PathVariable String username){
        return appUserService.findUserByUsername(username);
    }

    @PatchMapping("/{id}/reputation")
    public UserResponse updateUserReputation(@PathVariable Long id, @RequestParam int reputation){
        return appUserService.updateReputation(id, reputation);
    }
}
