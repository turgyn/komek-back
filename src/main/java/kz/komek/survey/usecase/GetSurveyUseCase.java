package kz.komek.survey.usecase;

import kz.komek.survey.dto.SurveyRs;
import kz.komek.survey.model.Option;
import kz.komek.survey.model.Question;
import kz.komek.survey.repository.OptionRepository;
import kz.komek.survey.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetSurveyUseCase {

    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;

    public SurveyRs execute() {
        List<Question> questions = questionRepository.findAll();
        questions.sort(Comparator.comparing(Question::getOrdering));
        var questionsRs = questions.stream()
                .map(question -> {
                    List<Option> options = optionRepository.findAllByQuestionId(question.getId());
                    options.sort(Comparator.comparing(Option::getOrdering));
                    return mapToDto(question, options);
                })
                .collect(Collectors.toList());
        return new SurveyRs(questionsRs);
    }

    private SurveyRs.QuestionRs mapToDto(Question question, List<Option> options) {
        return new SurveyRs.QuestionRs(question.getTitle(), options.stream().map(Option::getText).collect(Collectors.toList()));
    }

}
