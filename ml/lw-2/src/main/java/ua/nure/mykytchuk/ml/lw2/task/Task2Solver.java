package ua.nure.mykytchuk.ml.lw2.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.nure.mykytchuk.ml.lw2.dom.car.CarClass;
import ua.nure.mykytchuk.ml.lw2.hypothesys.finds.CarFindSService;

import java.util.Arrays;

@RequiredArgsConstructor
@Slf4j
@Service
public class Task2Solver implements TaskSolver {

    private final CarFindSService carFindSService;


    @Override
    public void solve() {
        separate(log);
        log.info("2.\tЗастосувати Find-S алгоритм.");
        Arrays.stream(CarClass.values())
                .forEach(carClass -> log.info("{}", carFindSService.findSWithCarClass(carClass)));
    }
}
