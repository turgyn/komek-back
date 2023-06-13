package kz.komek.survey.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "options")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long questionId;
    private String text;
}
