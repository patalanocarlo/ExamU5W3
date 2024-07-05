package PatalanoCarlo.ExamU5W3.Repository;

import PatalanoCarlo.ExamU5W3.Entites.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByOrganizerId(Long organizerId);
}