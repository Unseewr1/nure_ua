package ua.nure.mykytchuk.ml.lw1.commons;

import java.util.Comparator;
import java.util.Random;

public class RandomComparator<T> implements Comparator<T> {

    private final Random random = new Random();


    @SuppressWarnings("all")
    @Override
    public int compare(T o1, T o2) {
        return random.nextInt(-1, 2);
    }

    @SuppressWarnings("all")
    @Override
    public boolean equals(Object obj) {
        return random.nextBoolean();
    }
}
