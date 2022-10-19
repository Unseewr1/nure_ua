package ua.nure.mykytchuk.ml.lw2.hypothesys.candidateelimination;

import lombok.NonNull;
import ua.nure.mykytchuk.ml.lw2.dom.car.Car;
import ua.nure.mykytchuk.ml.lw2.dom.car.CarClass;
import ua.nure.mykytchuk.ml.lw2.exception.initialization.CarVersionLoggingCandidateEliminatorNotInitializedException;
import ua.nure.mykytchuk.ml.lw2.formatting.CarFormatService;

public abstract class LoggingCarCandidateEliminator extends LoggingCarSolver {

    protected final String logFormat;
    protected CarCandidateEliminator carCandidateEliminator;


    protected LoggingCarCandidateEliminator(
            String logFormat,
            CarFormatService carFormatService) {
        super(carFormatService);
        this.logFormat = logFormat;
    }

    public void init(@NonNull CarClass carClass) {
        carCandidateEliminator = CarCandidateEliminator.of(carClass);
    }


    protected boolean canObtainNewResult() {
        checkInitialization();
        return carCandidateEliminator.getSCar()
                .hasAnyKnownField();
    }

    protected void checkInitialization() {
        if (carCandidateEliminator == null) {
            throw new CarVersionLoggingCandidateEliminatorNotInitializedException();
        }
    }


    abstract void eliminate(@NonNull Car car);
}
