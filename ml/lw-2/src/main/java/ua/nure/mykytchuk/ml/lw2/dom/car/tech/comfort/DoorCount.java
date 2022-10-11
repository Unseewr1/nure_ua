package ua.nure.mykytchuk.ml.lw2.dom.car.tech.comfort;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ua.nure.mykytchuk.ml.lw2.dom.car.price.BuyingPrice;

@RequiredArgsConstructor
@Getter(onMethod = @__(@JsonValue))
public enum DoorCount {

    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE_OR_MORE("5more"),
    UNKNOWN("?");


    @NonNull
    private final String csvValue;


    public boolean isKnown() {
        return (this != UNKNOWN);
    }

    public boolean correlatesTo(@NonNull DoorCount doorCount) {
        return (!isKnown() || (this == doorCount));
    }


    @Override
    public String toString() {
        return csvValue;
    }
}
