package kz.komek.recommendation.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "sessions")
@NoArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long specialsId;
    private LocalDateTime dateTime;
    private Boolean confirmed;

    public static Session build(Long userId, Long specialsId) {
        Session session = new Session();
        session.setUserId(userId);
        session.setSpecialsId(specialsId);
        session.setDateTime(LocalDateTime.now());
        session.setConfirmed(false);
        return session;
    }
}
