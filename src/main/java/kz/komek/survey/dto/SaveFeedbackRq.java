package kz.komek.survey.dto;

import lombok.Data;

import java.util.List;

@Data
public class SaveFeedbackRq {
    private List<String> feedbacks;
}
