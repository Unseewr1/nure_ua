package ua.nure.mykytchuk.ml.lw2.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.nure.mykytchuk.ml.lw2.dom.car.CarClass;
import ua.nure.mykytchuk.ml.lw2.hypothesys.candidateelimination.CarCandidateEliminationService;

import java.util.Arrays;

@RequiredArgsConstructor
@Slf4j
@Service
public class Task3Solver implements TaskSolver {

    private final CarCandidateEliminationService carCandidateEliminationService;


    @Override
    public void solve() {
        separate(log);
        log.info("3.\tЗастосувати Candidate-Elimination алгоритм та показати послідовність S та G множин.");
        Arrays.stream(CarClass.values())
                .forEach(carClass -> {
                    log.info("candidate elimination for cars by class \"{}\":", carClass);
                    carCandidateEliminationService.eliminateByCarClass(carClass);
                });
    }
}


