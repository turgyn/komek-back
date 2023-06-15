package kz.komek.auth.usecase;

import kz.komek.auth.AuthUtil;
import kz.komek.auth.controller.dto.MessageResponse;
import kz.komek.auth.controller.dto.SignupRequest;
import kz.komek.auth.user.User;
import kz.komek.auth.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RegistrationUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AuthUtil authUtil;

    public ResponseEntity<?> execute(SignupRequest signUpRequest) {
        if (userRepository.existsByPhoneNumber(signUpRequest.getPhoneNumber())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: PhoneNumber is already taken!"));
        }

        // Create new user's account
        User user = User.buildClient(
                signUpRequest.getPhoneNumber(),
                signUpRequest.getFullName(),
                encoder.encode(signUpRequest.getPassword()));

        userRepository.save(user);

        return authUtil.authenticate(signUpRequest.getPhoneNumber(), signUpRequest.getPassword());
    }
}
