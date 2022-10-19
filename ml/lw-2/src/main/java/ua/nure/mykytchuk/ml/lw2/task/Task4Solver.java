package ua.nure.mykytchuk.ml.lw2.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.mykytchuk.ml.lw2.dom.car.CarClass;
import ua.nure.mykytchuk.ml.lw2.hypothesys.candidateelimination.CarCandidateEliminationRunner;

import java.util.Arrays;

@Slf4j
@Service
public class Task4Solver extends CandidateEliminationTaskSolver {

    @Autowired
    public Task4Solver(CarCandidateEliminationRunner runner) {
        super(runner);
    }


    @Override
    public void solve() {
        separate(log);
        log.info("4.\tРеалізувати процедуру відновлення простору версій з S та G множин.");
        Arrays.stream(CarClass.values())
                .forEach(carClass -> {
                    log.info("candidate elimination for cars by class \"{}\":", carClass);
                    runner.runByCarClassWithVersions(carClass);
                });
    }
}
