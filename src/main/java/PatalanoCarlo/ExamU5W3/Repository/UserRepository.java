package PatalanoCarlo.ExamU5W3.Repository;

import PatalanoCarlo.ExamU5W3.Entites.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Utente, Long> {
    Optional<Utente> findByUsername(String username);
}