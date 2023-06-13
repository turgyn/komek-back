package kz.komek.recommendation.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "specialists")
public class Specialists {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String imageUrl;
    private String description;
    private String experience;
    private String diploma;
    private String courses;
}
