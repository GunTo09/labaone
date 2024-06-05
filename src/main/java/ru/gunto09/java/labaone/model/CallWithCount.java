package ru.gunto09.java.labaone.model;

import lombok.Data;
import ru.gunto09.java.labaone.repository.JokeReposiroty;

@Data
public class CallWithCount extends Call{
    Joke joke = new Joke();
    public CallWithCount(Long jokes_id, long count) {
        joke.setId(jokes_id);
        this.count = count;
    }

    public long count;
}
