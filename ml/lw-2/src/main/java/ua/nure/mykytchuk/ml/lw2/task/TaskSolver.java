package ua.nure.mykytchuk.ml.lw2.task;

import org.slf4j.Logger;

public interface TaskSolver {

    String SEPARATOR = "-";
    int SEPARATOR_COUNT = 100;
    String REPEATED_SEPARATOR = SEPARATOR.repeat(SEPARATOR_COUNT);

    default void separate(Logger log) {
        log.info(REPEATED_SEPARATOR);
    }

    void solve();
}
