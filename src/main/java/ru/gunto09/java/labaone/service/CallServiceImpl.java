package ru.gunto09.java.labaone.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gunto09.java.labaone.model.Call;
import ru.gunto09.java.labaone.repository.CallRepository;


@RequiredArgsConstructor
@Service
public class CallServiceImpl {

    private final CallRepository callReposiroty;



}
