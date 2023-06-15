package kz.komek.recommendation.dto;

import kz.komek.recommendation.model.Method;
import kz.komek.recommendation.model.Specialist;
import kz.komek.recommendation.model.Topic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialistDto {
    private Long id;
    private String fullname;
    private String image;
    private List<String> topics;
    private List<String> methods;
    private String experience;
    private String courses;
    private String diploma;
    private String description;

    public static SpecialistDto build(Specialist specialist, List<String> methods, List<String> topics) {
        SpecialistDto spec = new SpecialistDto();

        spec.setId(specialist.getId());
        spec.setFullname(specialist.getFullName());
        spec.setExperience(specialist.getExperience());
        spec.setCourses(specialist.getCourses());
        spec.setDiploma(specialist.getDiploma());
        spec.setDescription(specialist.getDescription());

        spec.setMethods(methods);
        spec.setTopics(topics);

//        String imagePath = "images/" + specialist.getImageUrl();
//        Path imageFilePath = Paths.get(imagePath);
//        byte[] imageBytes = new byte[0];
//        try {
//            imageBytes = Files.readAllBytes(imageFilePath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        spec.setImage(specialist.getImageUrl());

        return spec;
    }
}
