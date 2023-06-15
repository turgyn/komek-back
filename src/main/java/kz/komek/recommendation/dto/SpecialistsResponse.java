package kz.komek.recommendation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SpecialistsResponse {
    private List<SpecialistDto> specialists;
}
