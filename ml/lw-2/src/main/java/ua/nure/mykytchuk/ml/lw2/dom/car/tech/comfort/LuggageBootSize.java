package ua.nure.mykytchuk.ml.lw2.dom.car.tech.comfort;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter(onMethod = @__(@JsonValue))
public enum LuggageBootSize {

    SMALL("small"),
    MEDIUM("med"),
    BIG("big"),
    UNKNOWN("?");


    @NonNull
    private final String csvValue;


    public boolean isKnown() {
        return (this != UNKNOWN);
    }

    public boolean correlatesTo(@NonNull LuggageBootSize luggageBootSize) {
        return (!isKnown() || (this == luggageBootSize));
    }


    @Override
    public String toString() {
        return csvValue;
    }
}
