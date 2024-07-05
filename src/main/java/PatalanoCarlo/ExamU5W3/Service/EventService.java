package PatalanoCarlo.ExamU5W3.Service;

import PatalanoCarlo.ExamU5W3.DTO.EventDTO;
import PatalanoCarlo.ExamU5W3.Entites.Event;
import PatalanoCarlo.ExamU5W3.Entites.Utente;
import PatalanoCarlo.ExamU5W3.Repository.EventRepository;
import PatalanoCarlo.ExamU5W3.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;


    public List<Event> getEventsByOrganizerId(Long organizerId) {
        return eventRepository.findByOrganizerId(organizerId);
    }
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
    public Event createEvent(EventDTO eventDTO) {
        Event event = new Event();
        event.setTitle(eventDTO.getTitle());
        event.setDescription(eventDTO.getDescription());
        event.setDate(eventDTO.getDate());
        event.setLocation(eventDTO.getLocation());
        event.setAvailableSeats(eventDTO.getAvailableSeats());

        Utente organizer = userRepository.findById(eventDTO.getOrganizerId())
                .orElseThrow(() -> new RuntimeException("Nessun organizzatore trovato con id: " + eventDTO.getOrganizerId()));
        event.setOrganizer(organizer);
        return eventRepository.save(event);
    }
}
