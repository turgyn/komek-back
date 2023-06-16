package kz.komek.recommendation.useCase;

import kz.komek.auth.AuthUtil;
import kz.komek.auth.controller.dto.MessageResponse;
import kz.komek.auth.user.User;
import kz.komek.auth.user.UserRepository;
import kz.komek.recommendation.dto.SaveSessionRq;
import kz.komek.recommendation.model.Session;
import kz.komek.recommendation.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveSessionUseCase {

    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;

    public ResponseEntity<?> execute(SaveSessionRq saveSessionRq) {
        if (!AuthUtil.isLogged()) {
            return AuthUtil.notAuthorized();
        }

        String phoneNumber = AuthUtil.getCurUserPhoneNumber();
        User user = userRepository.findByPhoneNumber(phoneNumber).get();

        Session session = Session.build(user.getId(), saveSessionRq.getSpecialistId());
        sessionRepository.save(session);
        return ResponseEntity.ok(new MessageResponse("saved"));
    }
}
