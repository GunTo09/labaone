package ru.gunto09.java.labaone.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.gunto09.java.labaone.model.Call;
import ru.gunto09.java.labaone.model.CallWithCount;

import java.util.List;

public interface CallRepository extends JpaRepository<Call, Long> {


    @Query(value = "SELECT i.joke_id, COUNT(i.id) AS call_count FROM jokes o LEFT JOIN jokes_call i ON o.id = i.joke_id GROUP BY i.joke_id ORDER BY call_count DESC", nativeQuery = true)
    Page<List<CallWithCount>> findTopFive(PageRequest pageRequest);

}
