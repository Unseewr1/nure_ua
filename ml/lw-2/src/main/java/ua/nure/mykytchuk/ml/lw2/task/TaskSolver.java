package ua.nure.mykytchuk.ml.lw2.task;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Lookup;
import ua.nure.mykytchuk.ml.lw2.repo.CarRepository;

public interface TaskSolver {

    String SEPARATOR = "-";
    int SEPARATOR_COUNT = 100;
    String REPEATED_SEPARATOR = SEPARATOR.repeat(SEPARATOR_COUNT);

    @Lookup
    default CarRepository getCarRepository() {
        return null;
    }

    default void separate(Logger log) {
        log.info(REPEATED_SEPARATOR);
    }

    void solve();
}
