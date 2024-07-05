package PatalanoCarlo.ExamU5W3.Entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Event {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String title;

        private String description;

        private LocalDateTime date;

        private String location;

        private int availableSeats;

        @ManyToOne
        @JoinColumn(name = "organizer_id")
        private Utente organizer;
    }

