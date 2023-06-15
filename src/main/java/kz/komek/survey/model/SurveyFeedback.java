package kz.komek.survey.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "survey_feedback")
@NoArgsConstructor
public class SurveyFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String text;

    public SurveyFeedback(Long userId, String text) {
        this.userId = userId;
        this.text = text;
    }
}
