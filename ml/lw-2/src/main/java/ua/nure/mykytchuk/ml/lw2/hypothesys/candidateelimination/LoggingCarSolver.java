package ua.nure.mykytchuk.ml.lw2.hypothesys.candidateelimination;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ua.nure.mykytchuk.ml.lw2.formatting.CarFormatService;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class LoggingCarSolver {

    @Getter(AccessLevel.PROTECTED)
    private final CarFormatService carFormatService;
}
