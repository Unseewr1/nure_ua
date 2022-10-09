package ua.nure.mykytchuk.ml.lw1.commons.math;

import lombok.NonNull;
import ua.nure.mykytchuk.ml.lw1.commons.pair.Pair;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class PopulationMeanCalculator<E> {

    private static final Set<Class<?>> ALLOWABLE_CLASSES = Set.copyOf(
            List.of(byte.class, int.class, long.class,
                    float.class, double.class));

    private final @NonNull List<Pair<String, Double>> results;


    public static <E> PopulationMeanCalculator<E> of(@NonNull Class<E> clazz, final @NonNull List<E> objects) {
        return new PopulationMeanCalculator<>(clazz, objects);
    }


    private PopulationMeanCalculator(@NonNull Class<E> clazz, final @NonNull List<E> objects) {
        results = Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> ALLOWABLE_CLASSES.contains(field.getType()))
                .map(field -> Pair.of(field.getName(), calculatePopulationMeanByField(field, objects)))
                .toList();
    }


    public @NonNull List<Pair<String, Double>> getResults() {
        return results;
    }


    private static <E> double calculatePopulationMeanByField(final @NonNull Field field, final @NonNull List<E> objects) {
        return objects.stream()
                .mapToDouble(object -> DoubleCalculator.getDoubleValue(field, object))
                .reduce(0.0, Double::sum) / objects.size();
    }
}
