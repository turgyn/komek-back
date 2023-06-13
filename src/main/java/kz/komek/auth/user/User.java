package kz.komek.auth.user;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String phoneNumber;
    private String fullName;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private Boolean isEnabled;

    public static User buildClient(String phoneNumber, String fullName, String password) {
        User user = new User();
        user.phoneNumber = phoneNumber;
        user.fullName = fullName;
        user.password = password;
        user.role = UserRole.ROLE_USER;
        user.isEnabled = true;
        return user;
    }
}
