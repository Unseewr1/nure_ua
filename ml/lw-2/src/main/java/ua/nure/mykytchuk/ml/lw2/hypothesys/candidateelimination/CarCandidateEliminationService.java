package ua.nure.mykytchuk.ml.lw2.hypothesys.candidateelimination;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.nure.mykytchuk.ml.lw2.dom.car.Car;
import ua.nure.mykytchuk.ml.lw2.dom.car.CarClass;
import ua.nure.mykytchuk.ml.lw2.hypothesys.CarHypothesisService;
import ua.nure.mykytchuk.ml.lw2.repo.CarRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class CarCandidateEliminationService implements CarHypothesisService {

    private static final String LOG_FORMAT = "step {}: S: {}, G: [{}], current: {} {}";

    @NonNull
    private final CarRepository carRepository;


    public @NonNull void eliminateByCarClass(@NonNull CarClass carClass) {
        List<Car> cars = carRepository.findAll();
        CarCandidateEliminator carCandidateEliminator = CarCandidateEliminator.of(carClass);
        AtomicReference<Integer> step = new AtomicReference<>(INITIAL_STEP_INDEX);
        cars.stream()
                .takeWhile(car -> carCandidateEliminator.canChangeResult())
                .forEach(car -> {
                    carCandidateEliminator.eliminate(car);
                    log.info(
                            LOG_FORMAT,
                            step.getAndSet(step.get() + 1),
                            formatted(carCandidateEliminator.getSCar()),
                            carCandidateEliminator.getGSet().stream()
                                    .map(this::formatted)
                                    .collect(Collectors.joining(", ")),
                            (carCandidateEliminator.isPositive(car) ? "positive" : "negative"),
                            formatted(car));
                });
    }
}


