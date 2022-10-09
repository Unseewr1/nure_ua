package ua.nure.mykytchuk.ml.lw1.task;

import lombok.NonNull;
import org.slf4j.Logger;

public interface TaskSolver {

    int SEPARATOR_COUNT = 100;
    String TOTAL_SEPARATOR = "-".repeat(SEPARATOR_COUNT);


    void solve();

    default void separate(final @NonNull Logger log) {
        log.info(TOTAL_SEPARATOR);
    }
}
