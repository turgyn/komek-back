package kz.komek.recommendation.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "topics")
public class Topics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long specialistId;
    private String topic;
}
