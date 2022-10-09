package ua.nure.mykytchuk.ml.lw1.commons.math;

import lombok.NonNull;
import ua.nure.mykytchuk.ml.lw1.commons.pair.Pair;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DispersionCalculator<E> {

    private static final Set<Class<?>> ALLOWABLE_CLASSES = Set.copyOf(
            List.of(byte.class, int.class, long.class,
                    float.class, double.class));

    private final List<Pair<String, Double>> results;


    public static <E> DispersionCalculator<E> of(@NonNull Class<E> clazz, final @NonNull List<E> objects) {
        return new DispersionCalculator<>(clazz, objects);
    }


    private DispersionCalculator(@NonNull Class<E> clazz, final @NonNull List<E> objects) {
        Map<String, Double> populationMeans = PopulationMeanCalculator.of(clazz, objects).getResults().stream()
                .collect(Collectors.toMap(Pair::getKey, Pair::getValue));
        results = Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> ALLOWABLE_CLASSES.contains(field.getType()))
                .map(field -> Pair.of(field.getName(), calculateDispersionByField(field, objects, populationMeans.get(field.getName()))))
                .toList();
    }

    public List<Pair<String, Double>> getResults() {
        return results;
    }


    private static <E> double calculateDispersionByField(final @NonNull Field field, final @NonNull List<E> objects, double populationMean) {
        return objects.stream()
                .mapToDouble(object -> DoubleCalculator.getDoubleValue(field, object))
                .map(value -> Math.pow(value - populationMean, 2))
                .reduce(0.0, Double::sum) / objects.size();
    }
}
