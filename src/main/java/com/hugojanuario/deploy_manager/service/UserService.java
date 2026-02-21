package com.hugojanuario.deploy_manager.service;

import com.hugojanuario.deploy_manager.domain.user.User;
import com.hugojanuario.deploy_manager.domain.user.dto.UserCreateRequest;
import com.hugojanuario.deploy_manager.domain.user.dto.UserResponse;
import com.hugojanuario.deploy_manager.domain.user.dto.UserUpdateRequest;
import com.hugojanuario.deploy_manager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponse createUser(UserCreateRequest userCreateRequest){
        User user = new User();
        user.setActive(true);
        user.setNameUser(userCreateRequest.nameUser());
        user.setEmail(userCreateRequest.email());
        user.setPassword(userCreateRequest.password());
        user.setRoleType(userCreateRequest.roleType());

        var saved = userRepository.save(user);

        return new UserResponse(saved);
    }

    public Page <UserResponse> findAllUsers(Pageable pageable){

        return userRepository.findByActiveTrue(pageable).map(UserResponse::new);
    }

    public UserResponse findByIdUser(UUID id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found..."));

        return new UserResponse(user);
    }

    public UserResponse updateUser(UUID id, UserUpdateRequest userUpdateRequest){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found..."));

        if (userUpdateRequest.email() != null){
            user.setEmail(userUpdateRequest.email());
        }
        if (userUpdateRequest.password() != null){
            user.setPassword(userUpdateRequest.password());
        }
        if (userUpdateRequest.roleType() != null){
            user.setRoleType(userUpdateRequest.roleType());
        }

        var update = userRepository.save(user);

        return new UserResponse(update);

    }

    public void deleteUser(UUID id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found..."));

        user.setActive(false);
        userRepository.save(user);
    }
}
