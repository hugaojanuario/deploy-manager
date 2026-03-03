package com.hugojanuario.deploy_manager.controller;

import com.hugojanuario.deploy_manager.domain.user.dto.UserCreateRequest;
import com.hugojanuario.deploy_manager.domain.user.dto.UserUpdateRequest;
import com.hugojanuario.deploy_manager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity createUser(@RequestBody UserCreateRequest userCreateRequest){
        var newUser = userService.createUser(userCreateRequest);

        return ResponseEntity.ok().body(newUser);
    }

    @GetMapping
    public ResponseEntity findAllUsers(@PageableDefault(size = 2)Pageable pageable){
        var find = userService.findAllUsers(pageable);

        return ResponseEntity.ok(find);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable UUID id){
        var findById = userService.findByIdUser(id);

        return ResponseEntity.ok(findById);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser (@PathVariable UUID id, @RequestBody UserUpdateRequest userUpdateRequest){
        var update = userService.updateUser(id, userUpdateRequest);

        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity softDeleteUSer(UUID id){
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }



}
