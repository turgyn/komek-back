package kz.komek.survey.repository;

import kz.komek.survey.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Long> {
    List<Option> findAllByQuestionId(Long questionId);
}
