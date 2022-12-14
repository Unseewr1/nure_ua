package ua.nure.mykytchuk.ml.lw1.commons.math;

import lombok.NonNull;
import ua.nure.mykytchuk.ml.lw1.exception.DoubleValueGetterException;

import java.lang.reflect.Field;

public interface DoubleCalculator {

    @SuppressWarnings("all")
    static <E> double getDoubleValue(
            final @NonNull Field field,
            final @NonNull E object
    ) {
        boolean accessibility = field.isAccessible();
        try {
            field.setAccessible(true);
            return (double) field.get(object);
        } catch (IllegalAccessException e) {
            throw new DoubleValueGetterException();
        } finally {
            field.setAccessible(accessibility);
        }
    }
}
