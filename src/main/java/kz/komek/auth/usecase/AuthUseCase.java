package kz.komek.auth.usecase;

import kz.komek.auth.AuthUtil;
import kz.komek.auth.controller.dto.LoginRequest;
import kz.komek.auth.controller.dto.MessageResponse;
import kz.komek.auth.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthUseCase {

    private final UserRepository userRepository;
    private final AuthUtil authUtil;

    public ResponseEntity<?> execute(LoginRequest loginRequest) {
        log.info("starting signin");
        if (!userRepository.existsByPhoneNumber(loginRequest.getPhoneNumber())) {
            return ResponseEntity.badRequest().body(new MessageResponse("phoneNumber not found"));
        }
        return authUtil.authenticate(loginRequest.getPhoneNumber(), loginRequest.getPassword());
    }
}
