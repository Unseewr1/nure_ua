package ua.nure.mykytchuk.ml.lw2.hypothesys.finds;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.nure.mykytchuk.ml.lw2.dom.car.Car;
import ua.nure.mykytchuk.ml.lw2.dom.car.CarClass;
import ua.nure.mykytchuk.ml.lw2.formatting.CarFormatService;
import ua.nure.mykytchuk.ml.lw2.hypothesys.CarHypothesisService;
import ua.nure.mykytchuk.ml.lw2.hypothesys.candidateelimination.LoggingCarSolver;
import ua.nure.mykytchuk.ml.lw2.repo.CarRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
public class CarFindSService extends LoggingCarSolver {

    private static final int MINIMUM_CAR_COUNT = 1;
    private static final int INITIAL_STEP_VALUE = 1;

    private final String logFormat;
    private final CarRepository carRepository;


    @Autowired
    public CarFindSService(
            @Value("${car.log.format.find_s}") String logFormat,
            CarRepository carRepository,
            CarFormatService carFormatService
    ) {
        super(carFormatService);
        this.logFormat = logFormat;
        this.carRepository = carRepository;
    }


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
                        logFormat,
                        step.getAndSet(step.get() + 1),
                        getCarFormatService().format(carFindSer.getHypothesisCar()),
                        getCarFormatService().format(car),
                        getCarFormatService().format(carFindSer.updateHypothesis(car))
                ));
        return carFindSer.getHypothesisCar();
    }
}
