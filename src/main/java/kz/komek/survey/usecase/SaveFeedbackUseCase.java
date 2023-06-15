package kz.komek.survey.usecase;

import kz.komek.auth.AuthUtil;
import kz.komek.auth.user.User;
import kz.komek.auth.user.UserRepository;
import kz.komek.survey.dto.SaveFeedbackRq;
import kz.komek.survey.model.SurveyFeedback;
import kz.komek.survey.repository.SurveyFeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class SaveFeedbackUseCase {

    private final SurveyFeedbackRepository feedbackRepository;
    private final UserRepository userRepository;

    @Transactional
    public ResponseEntity<?> execute(SaveFeedbackRq saveFeedbackRq) {
        if (!AuthUtil.isLogged()) {
            return AuthUtil.notAuthorized();
        }
        String loggedPhone = AuthUtil.getCurUserPhoneNumber();
        User user = userRepository.findByPhoneNumber(loggedPhone).get();
        feedbackRepository.deleteAllByUserId(user.getId());
        saveFeedbackRq.getFeedbacks().forEach(feedback -> {
            feedbackRepository.save(new SurveyFeedback(user.getId(), feedback));
        });
        return ResponseEntity.ok().build();
    }
}
