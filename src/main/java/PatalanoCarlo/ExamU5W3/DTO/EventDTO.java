package PatalanoCarlo.ExamU5W3.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventDTO {
    private String title;
    private String description;
    private LocalDateTime date;
    private String location;
    private int availableSeats;
    private Long organizerId;
}