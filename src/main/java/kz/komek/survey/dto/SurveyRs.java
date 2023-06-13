package kz.komek.survey.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SurveyRs {
    private List<QuestionRs> questions;

    @Data
    @AllArgsConstructor
    public static class QuestionRs {
        private String title;
        private List<String> options;
    }
}
