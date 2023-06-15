package kz.komek.recommendation.controller;

import kz.komek.recommendation.useCase.GetSpecialistsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recommendation")
@RequiredArgsConstructor
public class RecommendationController {

    private final GetSpecialistsUseCase getSpecialistsUseCase;

    @GetMapping()
    public ResponseEntity<?> getSpecialists() {
        return getSpecialistsUseCase.getSpecialists();
    }
}
