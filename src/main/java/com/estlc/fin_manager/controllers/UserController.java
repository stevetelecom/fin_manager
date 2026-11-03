package com.estlc.fin_manager.controllers;

import com.estlc.fin_manager.dto.UserRequestDto;
import com.estlc.fin_manager.dto.UserResponseDto;
import com.estlc.fin_manager.services.interfaces.UserServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/users", "/api/v1/users"})
@RequiredArgsConstructor
public class UserController {

    private final UserServiceInterface userService;

    @GetMapping
    public List<UserResponseDto> findAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponseDto findById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto create(@RequestBody UserRequestDto dto) {
        return userService.saveUser(dto);
    }

    @PutMapping("/{id}")
    public UserResponseDto update(@PathVariable String id, @RequestBody UserRequestDto dto) {
        return userService.updateUser(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        userService.deleteUser(id);
    }
}
