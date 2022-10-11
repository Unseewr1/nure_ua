package ua.nure.mykytchuk.ml.lw2.dom.car.tech;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ua.nure.mykytchuk.ml.lw2.dom.car.tech.comfort.PersonCount;

@RequiredArgsConstructor
@Getter(onMethod = @__(@JsonValue))
public enum Safety {

    LOW("low"),
    MEDIUM("med"),
    HIGH("high"),
    UNKNOWN("?");


    @NonNull
    private final String csvValue;


    public boolean isKnown() {
        return (this != UNKNOWN);
    }

    public boolean correlatesTo(@NonNull Safety safety) {
        return (!isKnown() || (this == safety));
    }


    @Override
    public String toString() {
        return csvValue;
    }
}
