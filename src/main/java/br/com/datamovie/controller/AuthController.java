package br.com.datamovie.controller;

import br.com.datamovie.config.TokenService;
import br.com.datamovie.controller.request.LoginRequest;
import br.com.datamovie.controller.request.UserRequest;
import br.com.datamovie.controller.response.LoginResponse;
import br.com.datamovie.controller.response.UserResponse;
import br.com.datamovie.entity.User;
import br.com.datamovie.exception.UsernameOrPasswordInvalidException;
import br.com.datamovie.mapper.UserMapper;
import br.com.datamovie.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("datamovie/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @RequestMapping(value = "/validate", method = RequestMethod.HEAD)
    public ResponseEntity<Void> validateToken() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest) {
        User savedUser = userService.save(UserMapper.toUser(userRequest));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UserMapper.toUserResponse(savedUser));

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());
            Authentication authenticate = authenticationManager.authenticate(userAndPass);

            User user = (User) authenticate.getPrincipal();

            String token = tokenService.generateToken(user);

            return ResponseEntity.ok(new LoginResponse(token));

        } catch (BadCredentialsException exception) {
            throw new UsernameOrPasswordInvalidException("Invalid username or password");
        }
    }
}
