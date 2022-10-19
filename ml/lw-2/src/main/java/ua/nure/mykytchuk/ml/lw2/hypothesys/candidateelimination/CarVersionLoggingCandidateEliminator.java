package ua.nure.mykytchuk.ml.lw2.hypothesys.candidateelimination;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ua.nure.mykytchuk.ml.lw2.dom.car.Car;
import ua.nure.mykytchuk.ml.lw2.formatting.CarFormatService;

@Slf4j
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CarVersionLoggingCandidateEliminator extends LoggingCarCandidateEliminator {

    private static final int INITIAL_VERSION = 1;
    private int version = INITIAL_VERSION;


    public CarVersionLoggingCandidateEliminator(
            @Value("${car.log.format.candidate_elimination.with_version}") String logFormatWithVersion,
            CarFormatService carFormattingService) {
        super(logFormatWithVersion, carFormattingService);
    }


    @Override
    public void eliminate(@NonNull Car car) {
        checkInitialization();
        carCandidateEliminator.eliminate(car);
        log.info(
                logFormat,
                version,
                getCarFormatService().format(carCandidateEliminator.getSCar()),
                version,
                getCarFormatService().format(carCandidateEliminator.getGSet()),
                (carCandidateEliminator.isPositive(car) ? "!" : ""),
                getCarFormatService().format(car));
        version++;
    }
}