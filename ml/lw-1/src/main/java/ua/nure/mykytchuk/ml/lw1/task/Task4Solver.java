package ua.nure.mykytchuk.ml.lw1.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.nure.mykytchuk.ml.lw1.repo.IrisRepository;

import java.util.stream.IntStream;

@RequiredArgsConstructor
@Slf4j
@Service
public class Task4Solver implements TaskSolver {

    private final IrisRepository irisRepository;


    @Override
    public void solve() {
        separate(log);
        log.info("4.\tпроцедура призначення номерів класів для значень цільового атрибуту, яка замінює символьні значення класів їх порядковими номерами:");
        irisRepository.replaceIrisesClassesWithIds();
        IntStream.range(0, irisRepository.getIrisCount())
                .forEach(id -> log.info("Iris[%d]=%s".formatted(id, irisRepository.getById(id))));
    }
}
