package ru.gunto09.java.labaone.service;

import org.springframework.data.domain.Page;
import ru.gunto09.java.labaone.model.Joke;
import java.util.List;
import java.util.Optional;

public interface JokeService {

    void postJoke (Joke textJoke);

    Page<Joke> getAllJokes(int page, boolean sortByDate);

    Optional<Joke> getJokeById(Long id);

    Optional<Joke> putJoke(Long id, Joke textJoke);


    void deleteJoke(Long id);


}
