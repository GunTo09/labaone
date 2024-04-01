package ru.gunto09.java.labaone.service;

import ru.gunto09.java.labaone.model.Joke;
import java.util.List;
import java.util.Optional;

public interface JokeService {

    void postJoke (Joke textJoke);

    List<Joke> getAllJokes();

    Optional<Joke> getJokeById(Long id);

    Optional<Joke> putJoke(Long id, Joke textJoke);


    void deleteJoke(Long id);

}
