package kz.komek.auth.controller;

import kz.komek.auth.AuthUtil;
import kz.komek.auth.controller.dto.*;
import kz.komek.auth.usecase.AuthUseCase;
import kz.komek.auth.usecase.EditProfileUseCase;
import kz.komek.auth.usecase.RegistrationUseCase;
import kz.komek.auth.user.User;
import kz.komek.auth.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserRepository userRepository;
    private final EditProfileUseCase editProfileUseCase;
    private final RegistrationUseCase registrationUseCase;
    private final AuthUseCase authUseCase;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return authUseCase.execute(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return registrationUseCase.execute(signUpRequest);
    }

    @PostMapping("/profile")
    public ResponseEntity<?> editProfile(@Valid @RequestBody EditProfileRequest editProfileRequest) {
        return editProfileUseCase.execute(editProfileRequest);
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getUser() {
        if (!AuthUtil.isLogged()) {
            return ResponseEntity.badRequest().build();
        }
        String phoneNumber = AuthUtil.getCurUserPhoneNumber();
        User user = userRepository.findByPhoneNumber(phoneNumber).get();
        return ResponseEntity.ok(user);
    }
}
