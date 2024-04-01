package ru.gunto09.java.labaone.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.http.converter.json.GsonBuilderUtils;


import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "jokes")
@Table(name = "jokes")
public class Joke {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text_joke")
    private String textJoke;

    @Column(name = "added_date")
    @CreationTimestamp
    private LocalDateTime dateAdded;

    @Column(name = "changed_date")
    @UpdateTimestamp
    private LocalDateTime dateChanged;
}
