package ru.gunto09.java.labaone.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.gunto09.java.labaone.model.CallWithCount;
import ru.gunto09.java.labaone.model.Joke;

import java.util.List;

public interface JokeReposiroty extends PagingAndSortingRepository<Joke, Long>, JpaRepository<Joke, Long> {
    @Query (value = "SELECT o.id, o.text_joke, o.added_date, o.changed_date FROM jokes o ORDER BY RANDOM()", nativeQuery = true)
    Page<Joke> findRandom(Pageable pageable);

    @Query (value = "SELECT COUNT(*) FROM public.jokes", nativeQuery = true)
    int amountJokes();
}
