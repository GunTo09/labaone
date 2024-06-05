package ru.gunto09.java.labaone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gunto09.java.labaone.model.CallWithCount;
import ru.gunto09.java.labaone.model.Joke;
import ru.gunto09.java.labaone.repository.JokeReposiroty;
import ru.gunto09.java.labaone.service.JokeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jokes")
@RequiredArgsConstructor
public class JokeController {

    private final JokeService jokeService;
    private final JokeReposiroty jokeReposiroty;

    @PostMapping
    ResponseEntity<Void> postJoke (@RequestBody Joke textJoke){
        jokeService.postJoke(textJoke);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    ResponseEntity<Page<Joke>> getJokes(@RequestParam int page,
                                        @RequestParam boolean sortByDate){
        return ResponseEntity.ok(jokeService.getAllJokes(page, sortByDate));
    }

    @GetMapping("/{id}")
    ResponseEntity<Joke> getJokeById(@PathVariable Long id){
        return jokeService.getJokeById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    ResponseEntity<Joke> updateJoke(@PathVariable Long id, @RequestBody Joke textJoke){
        Optional<Joke> changedJoke = jokeService.putJoke(id, textJoke);
        return changedJoke.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteJoke(@PathVariable Long id){
        jokeService.deleteJoke(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/random")
    public ResponseEntity<Page<Joke>> getRandom() {
        return ResponseEntity.ok(jokeReposiroty.findRandom(PageRequest.of(0, 1)));
    }
}
