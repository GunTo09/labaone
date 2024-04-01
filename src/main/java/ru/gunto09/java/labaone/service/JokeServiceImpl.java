package ru.gunto09.java.labaone.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.gunto09.java.labaone.model.Joke;
import ru.gunto09.java.labaone.repository.JokeReposiroty;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class JokeServiceImpl implements JokeService {

    private final JokeReposiroty jokeReposiroty;

    @Override
    public void postJoke(Joke textJoke) {
        jokeReposiroty.save(textJoke);
    }

    @Override
    public List<Joke> getAllJokes() {
        return jokeReposiroty.findAll();
    }

    @Override
    public Optional<Joke> getJokeById(Long id) {
        return jokeReposiroty.findById(id);
    }

    @Override
    public Optional<Joke> putJoke(Long id, Joke textJoke) {
        Optional<Joke> changedJoke = jokeReposiroty.findById(id);
        if (changedJoke.isPresent()) {
            Joke jokeToUpdate = changedJoke.get();
            jokeToUpdate.setTextJoke(textJoke.getTextJoke());
            jokeReposiroty.save(jokeToUpdate);
        }
        return changedJoke;
    }

    @Override
    public void deleteJoke(Long id) {
        jokeReposiroty.deleteById(id);
    }
}
