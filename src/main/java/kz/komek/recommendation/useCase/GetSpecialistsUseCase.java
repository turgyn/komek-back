package kz.komek.recommendation.useCase;

import kz.komek.auth.AuthUtil;
import kz.komek.auth.user.User;
import kz.komek.auth.user.UserRepository;
import kz.komek.recommendation.dto.SpecialistDto;
import kz.komek.recommendation.dto.SpecialistsResponse;
import kz.komek.recommendation.model.Method;
import kz.komek.recommendation.model.Topic;
import kz.komek.recommendation.repository.MethodRepository;
import kz.komek.recommendation.repository.SpecialistRepository;
import kz.komek.recommendation.repository.TopicRepository;
import kz.komek.survey.model.SurveyFeedback;
import kz.komek.survey.repository.SurveyFeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetSpecialistsUseCase {

    private final UserRepository userRepository;
    private final SurveyFeedbackRepository feedbackRepository;
    private final SpecialistRepository specialistRepository;
    private final TopicRepository topicRepository;
    private final MethodRepository methodRepository;

    public ResponseEntity<?> getSpecialists() {
        if (!AuthUtil.isLogged()) {
            return AuthUtil.notAuthorized();
        }

        List<String> feedbacks = getFeedbacks();
        List<SpecialistDto> specialists = getAllSpecialists();

        List<SpecialistDto> bestSpecialists = matchData(specialists, feedbacks);
        return ResponseEntity.ok(new SpecialistsResponse(bestSpecialists));
    }

    public List<String> getFeedbacks() {
        String loggedPhone = AuthUtil.getCurUserPhoneNumber();
        User user = userRepository.findByPhoneNumber(loggedPhone).get();
        List<String> feedbacks = feedbackRepository.findAllByUserId(user.getId()).stream()
                .map(SurveyFeedback::getText)
                .collect(Collectors.toList());
        return feedbacks;
    }

    public List<SpecialistDto> getAllSpecialists() {
        return specialistRepository.findAll().stream()
                .map(spec -> {
                    List<String> topics = topicRepository.findAllBySpecialistId(spec.getId()).stream().map(Topic::getTopic).collect(Collectors.toList());
                    List<String> methods = methodRepository.findAllBySpecialistId(spec.getId()).stream().map(Method::getMethod).collect(Collectors.toList());
                    return SpecialistDto.build(spec, methods, topics);
                })
                .collect(Collectors.toList());
    }

    private List<SpecialistDto> matchData(List<SpecialistDto> specialists, List<String> feedbacks) {
        HashMap<SpecialistDto, Long> specAndCount = new HashMap<>();
        specialists.forEach(spec -> {
            HashSet<String> tags = new HashSet<>(spec.getTopics());
//            tags.addAll(spec.getTopics());

            int count = (int) feedbacks.stream().filter(tags::contains).count();
            specAndCount.put(spec, (long) count);
        });

        // Convert the HashMap to a list of map entries
        List<Map.Entry<SpecialistDto, Long>> entryList = new ArrayList<>(specAndCount.entrySet());

        // Sort the list in descending order based on the Long values
        Collections.sort(entryList, (entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        // Extract the top 5 SpecialistDto objects from the sorted list
        List<SpecialistDto> topSpecialists = new ArrayList<>();
        int count = 0;
        for (Map.Entry<SpecialistDto, Long> entry : entryList) {
            topSpecialists.add(entry.getKey());
            count++;
            if (count >= 5) {
                break;
            }
        }

        return topSpecialists;
    }
}
