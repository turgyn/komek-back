package kz.komek.survey.repository;

import kz.komek.survey.model.SurveyFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyFeedbackRepository extends JpaRepository<SurveyFeedback, Long> {

    void deleteAllByUserId(Long userId);
    List<SurveyFeedback> findAllByUserId(Long userId);
}
