package ua.nure.mykytchuk.ml.lw1.app;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import ua.nure.mykytchuk.ml.lw1.task.Task1Solver;
import ua.nure.mykytchuk.ml.lw1.task.Task2Solver;
import ua.nure.mykytchuk.ml.lw1.task.Task3Solver;
import ua.nure.mykytchuk.ml.lw1.task.Task4Solver;
import ua.nure.mykytchuk.ml.lw1.task.TaskSolver;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Slf4j
@Service
public class Lw1Executor implements CommandLineRunner {

    private final Task1Solver task1Solver;
    private final Task2Solver task2Solver;
    private final Task3Solver task3Solver;
    private final Task4Solver task4Solver;


    @Override
    public void run(
            String[] args
    ) {
        Stream.of(task1Solver, task2Solver, task3Solver, task4Solver)
                .forEach(TaskSolver::solve);
    }
}
