package PatalanoCarlo.ExamU5W3.Entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @OneToMany(mappedBy = "utente")
    private Set<Role> roles;

    @Column
    private String organizerToken;

    public Utente(String username, String password) {
        this.username = username;
        this.password = password;
    }
}