package ua.nure.mykytchuk.ml.lw1.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.nure.mykytchuk.ml.lw1.commons.math.DispersionCalculator;
import ua.nure.mykytchuk.ml.lw1.commons.math.PopulationMeanCalculator;
import ua.nure.mykytchuk.ml.lw1.dom.Iris;
import ua.nure.mykytchuk.ml.lw1.repo.IrisRepository;

import java.util.List;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Slf4j
@Service
public class Task3Solver implements TaskSolver {

    private final IrisRepository irisRepository;


    @Override
    public void solve() {
        separate(log);
        log.info("3.\tпроцедура обчислення статистичних характеристик для значень кожного атрибуту (мат. очікування та дисперсії):");
        List<Iris> irises = IntStream.range(0, irisRepository.getIrisCount())
                .mapToObj(irisRepository::getById)
                .toList();
        PopulationMeanCalculator.of(Iris.class, irises).getResults().stream()
                .map(pair -> "%s: %f".formatted(pair.getKey(), pair.getValue()))
                .forEach(fieldAndValue -> log.info("Математичне очікування поля " + fieldAndValue));
        DispersionCalculator.of(Iris.class, irises).getResults().stream()
                .map(pair -> "%s: %f".formatted(pair.getKey(), pair.getValue()))
                .forEach(fieldAndValue -> log.info("Дисперсія поля " + fieldAndValue));
    }
}


