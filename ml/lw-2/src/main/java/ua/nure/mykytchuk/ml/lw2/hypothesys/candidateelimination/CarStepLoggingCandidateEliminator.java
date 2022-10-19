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
public class CarStepLoggingCandidateEliminator extends LoggingCarCandidateEliminator {

    private static final int INITIAL_STEP = 1;

    private int step = INITIAL_STEP;


    protected CarStepLoggingCandidateEliminator(
            @Value("${car.log.format.candidate_elimination.with_steps}") String logFormatWithSteps,
            CarFormatService carFormattingService) {
        super(logFormatWithSteps, carFormattingService);
    }


    @Override
    void eliminate(@NonNull Car car) {
        carCandidateEliminator.eliminate(car);
        log.info(
                logFormat,
                step,
                getCarFormatService().format(carCandidateEliminator.getSCar()),
                getCarFormatService().format(carCandidateEliminator.getGSet()),
                (carCandidateEliminator.isPositive(car) ? "positive" : "negative"),
                getCarFormatService().format(car));
        step++;
    }
}
