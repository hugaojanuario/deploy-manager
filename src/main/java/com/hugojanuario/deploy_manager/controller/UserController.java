package com.hugojanuario.deploy_manager.controller;

import com.hugojanuario.deploy_manager.domain.user.dto.UserCreateRequest;
import com.hugojanuario.deploy_manager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;


}
