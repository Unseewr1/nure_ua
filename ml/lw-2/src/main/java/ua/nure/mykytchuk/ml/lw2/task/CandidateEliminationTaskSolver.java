package ua.nure.mykytchuk.ml.lw2.task;

import lombok.RequiredArgsConstructor;
import ua.nure.mykytchuk.ml.lw2.hypothesys.candidateelimination.CarCandidateEliminationRunner;

@RequiredArgsConstructor
public abstract class CandidateEliminationTaskSolver implements TaskSolver {

    protected final CarCandidateEliminationRunner runner;
}
