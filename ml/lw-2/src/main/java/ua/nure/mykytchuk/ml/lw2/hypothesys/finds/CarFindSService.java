package ua.nure.mykytchuk.ml.lw2.hypothesys.finds;

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

@RequiredArgsConstructor
@Slf4j
@Service
public class CarFindSService implements CarHypothesisService {

    private static final String LOG_FORMAT = "step {}: {} && {} -> {}";
    private static final int MINIMUM_CAR_COUNT = 1;
    private static final int INITIAL_STEP_VALUE = 1;

    private final CarRepository carRepository;


    public @NonNull Car findSWithCarClass(@NonNull CarClass carClass) {
        List<Car> carsByCarClass = carRepository.findByCarClass(carClass);
        if (carsByCarClass.isEmpty()) {
            Car hypothesisCar = new Car();
            hypothesisCar.setCarClass(carClass);
            return hypothesisCar;
        }
        CarFindSer carFindSer = CarFindSer.of(carsByCarClass.get(0));
        log.info("total cars by class \"{}\": {}", carClass, carsByCarClass.size());
        AtomicReference<Integer> step = new AtomicReference<>(INITIAL_STEP_VALUE);
        carsByCarClass.stream()
                .skip(MINIMUM_CAR_COUNT)
                .takeWhile(car -> carFindSer.anyOfFieldsIsKnown())
                .forEach(car -> log.info(
                        LOG_FORMAT,
                        step.getAndSet(step.get() + 1),
                        formatted(carFindSer.getHypothesisCar()),
                        formatted(car),
                        formatted(carFindSer.updateHypothesis(car))
                ));
        return carFindSer.getHypothesisCar();
    }
}
