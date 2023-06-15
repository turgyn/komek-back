package kz.komek.survey.controller;

import kz.komek.auth.AuthUtil;
import kz.komek.survey.dto.SaveFeedbackRq;
import kz.komek.survey.dto.SurveyRs;
import kz.komek.survey.usecase.GetSurveyUseCase;
import kz.komek.survey.usecase.SaveFeedbackUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/survey")
@RequiredArgsConstructor
public class SurveyController {

    private final GetSurveyUseCase getSurveyUseCase;
    private final SaveFeedbackUseCase saveFeedbackUseCase;

    @GetMapping
    public SurveyRs getSurvey() {
        return getSurveyUseCase.execute();
    }

    @PostMapping
    public ResponseEntity<?> postFeedback(@RequestBody SaveFeedbackRq saveFeedbackRq) {
        return saveFeedbackUseCase.execute(saveFeedbackRq);
    }
}
