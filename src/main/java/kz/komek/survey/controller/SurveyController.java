package kz.komek.survey.controller;

import kz.komek.survey.dto.SurveyRs;
import kz.komek.survey.usecase.GetSurveyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/survey")
@RequiredArgsConstructor
public class SurveyController {

    private final GetSurveyUseCase getSurveyUseCase;

    @GetMapping
//    @PreAuthorize("hasRole('USER')")
    public SurveyRs getSurvey() {
        return getSurveyUseCase.execute();
    }
}
