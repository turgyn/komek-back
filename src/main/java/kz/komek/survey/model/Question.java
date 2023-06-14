package kz.komek.survey.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Long ordering;
}
