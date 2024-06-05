package ru.gunto09.java.labaone.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.gunto09.java.labaone.model.Call;
import ru.gunto09.java.labaone.model.Joke;
import ru.gunto09.java.labaone.repository.CallRepository;
import ru.gunto09.java.labaone.repository.JokeReposiroty;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class JokeServiceImpl implements JokeService {

    private final JokeReposiroty jokeReposiroty;
    private final CallRepository callRepository;

    @Override
    public void postJoke(Joke textJoke) {
        jokeReposiroty.save(textJoke);
    }

    @Override
    public Page<Joke> getAllJokes(int page, boolean sortByDate) {
        int size = 3;
        return jokeReposiroty.findAll(PageRequest.of(page, size, Sort.by("dateAdded")));
    }

    @Override
    public Optional<Joke> getJokeById(Long id) {
        Optional<Joke> joke =jokeReposiroty.findById(id);
        callRepository.save(new Call(null, null, joke.get(), 1));
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
