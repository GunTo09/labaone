package ru.gunto09.java.labaone.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.gunto09.java.labaone.model.Joke;

public interface JokeReposiroty extends JpaRepository<Joke, Long> {
}
