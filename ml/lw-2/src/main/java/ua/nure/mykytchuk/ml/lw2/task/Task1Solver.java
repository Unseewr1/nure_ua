package ua.nure.mykytchuk.ml.lw2.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.nure.mykytchuk.ml.lw2.hypothesys.count.CarHypothesisCounter;

@RequiredArgsConstructor
@Slf4j
@Service
public class Task1Solver implements TaskSolver {

    private final CarHypothesisCounter carHypothesisCounter;


    public void solve() {
        separate(log);
        log.info("1.\tОбчислити кількість можливих прикладів та гіпотез.");
        log.info("total possible hypotheses: {}", carHypothesisCounter.count());
    }
}
