package ru.gunto09.java.labaone.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gunto09.java.labaone.model.Call;
import ru.gunto09.java.labaone.model.CallWithCount;
import ru.gunto09.java.labaone.repository.CallRepository;
import ru.gunto09.java.labaone.service.CallService;

import java.util.List;

@RestController
@RequestMapping("/call")
@RequiredArgsConstructor
@Slf4j
public class CallController {

    private final CallRepository callRepository;

    @GetMapping("/findTopFive")
    public ResponseEntity<Page<List<CallWithCount>>> getTopFive() {
        return ResponseEntity.ok(callRepository.findTopFive(PageRequest.of(0,5)));
    }

}
