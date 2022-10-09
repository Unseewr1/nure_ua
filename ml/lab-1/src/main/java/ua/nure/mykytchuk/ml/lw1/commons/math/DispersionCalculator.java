package ua.nure.mykytchuk.ml.lw1.commons.math;

import ua.nure.mykytchuk.ml.lw1.commons.pair.Pair;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class DispersionCalculator<E> {

    private static final Set<Class<?>> ALLOWABLE_CLASSES = Set.copyOf(
            List.of(byte.class, int.class, long.class,
                    float.class, double.class));

    private final List<Pair<String, Double>> results;


    public static <E> DispersionCalculator<E> of(Class<E> clazz, List<E> objects) {
        return new DispersionCalculator<>(clazz, objects);
    }


    private DispersionCalculator(Class<E> clazz, List<E> objects) {
        results = Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> ALLOWABLE_CLASSES.contains(field.getType()))
                .map(field -> Pair.of(field.getName(), calculateDispersionByField(field, objects)))
                .toList();
    }

    public List<Pair<String, Double>> getResults() {
        return results;
    }


    private static <E> double calculateDispersionByField(Field field, List<E> objects) {
        return objects.stream()
                .mapToDouble(object -> DoubleCalculator.getDoubleValue(field, object))
                .map(value -> Math.pow(value, 2))
                .reduce(0.0, Double::sum) / objects.size();
    }
}
