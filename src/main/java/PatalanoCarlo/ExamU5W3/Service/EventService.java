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

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getEventsByOrganizerId(Long organizerId) {
        return eventRepository.findByOrganizerId(organizerId);
    }

    public Event createEvent(EventDTO eventDTO) {
        Utente organizer = userRepository.findById(eventDTO.getOrganizerId())
                .orElseThrow(() -> new RuntimeException("Nessun organizzatore trovato con id: " + eventDTO.getOrganizerId()));

        Event event = new Event();
        event.setTitle(eventDTO.getTitle());
        event.setDescription(eventDTO.getDescription());
        event.setDate(eventDTO.getDate());
        event.setLocation(eventDTO.getLocation());
        event.setAvailableSeats(eventDTO.getAvailableSeats());
        event.setOrganizer(organizer);

        return eventRepository.save(event);
    }

    public void deleteEvent(Long eventId, Long organizerId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Evento non trovato con id: " + eventId));

        if (!event.getOrganizer().getId().equals(organizerId)) {
            throw new RuntimeException("Non sei autorizzato a cancellare questo evento");
        }

        eventRepository.deleteById(eventId);
    }
}