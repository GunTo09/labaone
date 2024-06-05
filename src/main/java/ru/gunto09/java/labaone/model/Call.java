package ru.gunto09.java.labaone.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "jokes_call")
@Table(name = "jokes_call")
public class Call {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "call_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "call_id_seq", sequenceName = "call_id_seq", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "date_call")
    @CreationTimestamp
    private LocalDateTime dateCall;

    @ManyToOne
    @JoinColumn(name = "joke_id")
    private Joke joke;

    @Column(name = "user_id")
    private int userId;

}
