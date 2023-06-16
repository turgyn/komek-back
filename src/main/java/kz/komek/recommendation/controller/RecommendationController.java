package kz.komek.recommendation.controller;

import kz.komek.recommendation.dto.SaveSessionRq;
import kz.komek.recommendation.useCase.GetSpecialistsUseCase;
import kz.komek.recommendation.useCase.SaveSessionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recommendation")
@RequiredArgsConstructor
public class RecommendationController {

    private final GetSpecialistsUseCase getSpecialistsUseCase;
    private final SaveSessionUseCase saveSessionUseCase;

    @GetMapping()
    public ResponseEntity<?> getSpecialists() {
        return getSpecialistsUseCase.getSpecialists();
    }

    @PostMapping()
    public ResponseEntity<?> postApplication(@RequestBody SaveSessionRq saveSessionRq) {
        return saveSessionUseCase.execute(saveSessionRq);
    }
}
