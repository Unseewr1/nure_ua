package ua.nure.mykytchuk.ml.lw1.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.nure.mykytchuk.ml.lw1.repo.IrisRepository;

import java.util.stream.IntStream;

@RequiredArgsConstructor
@Slf4j
@Service
public class Task1Solver implements TaskSolver {

    private final IrisRepository irisRepository;


    public void solve() {
        separate(log);
        log.info("1.\tпроцедура доступу до довільного елемента вибірки:");
        IntStream.range(0, irisRepository.getIrisCount())
                .forEach(id -> log.info("Iris[%d]=%s".formatted(id, irisRepository.getById(id))));
    }
}
