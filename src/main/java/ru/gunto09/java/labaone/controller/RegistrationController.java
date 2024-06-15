package ru.gunto09.java.labaone.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gunto09.java.labaone.service.UserService;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/registration")
@Slf4j
public class RegistrationController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> registration(
            @RequestParam("username") String username,
            @RequestParam("password")String password
    ){

        userService.registration(username, password);
        return ResponseEntity.ok().build();

    }

}
