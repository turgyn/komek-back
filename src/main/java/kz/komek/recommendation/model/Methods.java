package kz.komek.recommendation.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "methods")
public class Methods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long specialistId;
    private String method;
}

