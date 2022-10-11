package ua.nure.mykytchuk.ml.lw2.dom.car.tech.comfort;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ua.nure.mykytchuk.ml.lw2.dom.car.price.BuyingPrice;

@RequiredArgsConstructor
@Getter(onMethod = @__(@JsonValue))
public enum PersonCount {

    TWO("2"),
    FOUR("4"),
    MORE("more"),
    UNKNOWN("?");


    @NonNull
    private final String csvValue;


    public boolean isKnown() {
        return (this != UNKNOWN);
    }

    public boolean correlatesTo(@NonNull PersonCount personCount) {
        return (!isKnown() || (this == personCount));
    }


    @Override
    public String toString() {
        return csvValue;
    }
}
