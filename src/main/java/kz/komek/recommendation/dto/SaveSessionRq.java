package kz.komek.recommendation.dto;

import lombok.Data;

@Data
public class SaveSessionRq {
    private Long userId;
    private Long specialistId;
}
