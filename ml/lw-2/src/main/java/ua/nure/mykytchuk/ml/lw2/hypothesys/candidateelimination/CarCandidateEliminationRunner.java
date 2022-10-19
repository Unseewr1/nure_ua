package ua.nure.mykytchuk.ml.lw2.hypothesys.candidateelimination;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.nure.mykytchuk.ml.lw2.dom.car.Car;
import ua.nure.mykytchuk.ml.lw2.dom.car.CarClass;
import ua.nure.mykytchuk.ml.lw2.formatting.CarFormatService;
import ua.nure.mykytchuk.ml.lw2.repo.CarRepository;

import java.util.List;

@Slf4j
@Service
public class CarCandidateEliminationRunner {

    @NonNull
    private final String resultFormat;
    @NonNull
    private final CarRepository carRepository;
    @NonNull
    private final CarFormatService carFormattingService;

    @Autowired
    public CarCandidateEliminationRunner(
            @Value("${car.log.format.result}") String resultFormat,
            CarRepository carRepository,
            CarFormatService carFormattingService
    ) {
        this.resultFormat = resultFormat;
        this.carRepository = carRepository;
        this.carFormattingService = carFormattingService;
    }

    @Lookup
    public CarVersionLoggingCandidateEliminator getCarVersionLoggingCandidateEliminator() {
        return null;
    }

    @Lookup
    public CarStepLoggingCandidateEliminator getCarStepLoggingCandidateEliminator() {
        return null;
    }


    public void runByCarClass(@NonNull CarClass carClass) {
        runWith(carClass, getCarStepLoggingCandidateEliminator());
    }

    public void runByCarClassWithVersions(@NonNull CarClass carClass) {
        runWith(carClass, getCarVersionLoggingCandidateEliminator());
    }


    private void runWith(
            @NonNull CarClass carClass,
            @NonNull LoggingCarCandidateEliminator eliminator
    ) {
        List<Car> cars = carRepository.findAll();
        eliminator.init(carClass);
        CarCandidateEliminator carCandidateEliminator = CarCandidateEliminator.of(carClass);
        cars.stream()
                .takeWhile(car -> carCandidateEliminator.canChangeResult())
                .forEach(eliminator::eliminate);
        log.info(
                resultFormat,
                carFormattingService.format(carCandidateEliminator.getSCar()),
                carFormattingService.format(carCandidateEliminator.getGSet()));
    }
}


