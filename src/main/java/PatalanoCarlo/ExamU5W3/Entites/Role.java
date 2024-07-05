package PatalanoCarlo.ExamU5W3.Entites;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Utente utente;


    public Role(String name, Utente user) {
        this.name = name;
        this.utente = user;
    }
}
