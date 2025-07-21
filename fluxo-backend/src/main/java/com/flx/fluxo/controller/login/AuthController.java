package com.flx.fluxo.controller.login;

import com.flx.fluxo.model.login.LoginRequestDTO;
import com.flx.fluxo.model.login.RegisterRequestDTO;
import com.flx.fluxo.model.login.ResponseDTO;
import com.flx.fluxo.model.login.User;
import com.flx.fluxo.service.login.TokenService;
import com.flx.fluxo.service.login.UserService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body) {
        val user = userService.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(body.password(), user.getPassword())){
            val token = tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body) {
        val user = userService.findByEmail(body.email());

        if (user.isEmpty()){
            val hashedPassword = passwordEncoder.encode(body.password());
            val newUser = new User(null, body.name(), body.email(), hashedPassword);
            userService.save(newUser);


            val token = tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getName(), token));
        }

        return ResponseEntity.badRequest().build();
    }

}
