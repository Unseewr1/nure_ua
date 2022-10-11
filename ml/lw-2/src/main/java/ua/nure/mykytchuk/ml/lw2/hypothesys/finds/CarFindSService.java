package ua.nure.mykytchuk.ml.lw2.hypothesys.finds;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.nure.mykytchuk.ml.lw2.dom.car.Car;
import ua.nure.mykytchuk.ml.lw2.dom.car.CarClass;
import ua.nure.mykytchuk.ml.lw2.exception.NotEnoughDataException;
import ua.nure.mykytchuk.ml.lw2.hypothesys.CarHypothesisService;
import ua.nure.mykytchuk.ml.lw2.repo.CarRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RequiredArgsConstructor
@Slf4j
@Service
public class CarFindSService implements CarHypothesisService {

    private static final String LOG_FORMAT = "step {}: {} && {} -> {}";
    private static final int SKIPPED_CARS_COUNT = 1;

    private final CarRepository carRepository;


    public @NonNull Car findSWithCarClass(@NonNull CarClass carClass) {
        List<Car> carsByCarClass = carRepository.findByCarClass(carClass);
        if (carsByCarClass.isEmpty()) {
            throw new NotEnoughDataException();
        }
        Car hypothesisCar = carsByCarClass.get(SKIPPED_CARS_COUNT - 1);
        CarFindSer carFindSer = CarFindSer.of(hypothesisCar);
        log.info("total cars by class \"{}\": {}", carClass, carsByCarClass.size());
        AtomicReference<Integer> step = new AtomicReference<>(1);
        carsByCarClass.stream()
                .skip(SKIPPED_CARS_COUNT)
                .takeWhile(car -> carFindSer.anyOfFieldsIsKnown())
                .forEach(car -> log.info(
                        LOG_FORMAT,
                        step.getAndSet(step.get() + 1),
                        formatted(hypothesisCar),
                        formatted(car),
                        formatted(carFindSer.checkHypothesis(car))
                ));
        return hypothesisCar;
    }
}
