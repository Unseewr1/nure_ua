package ua.nure.mykytchuk.ml.lw2.dom.car.price;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter(onMethod = @__(@JsonValue))
public enum BuyingPrice {

    VERY_HIGH("vhigh"),
    HIGH("high"),
    MEDIUM("med"),
    LOW("low"),
    UNKNOWN("?");


    @NonNull
    private final String csvValue;


    public boolean isKnown() {
        return (this != UNKNOWN);
    }

    public boolean correlatesTo(@NonNull BuyingPrice buyingPrice) {
        return (!isKnown() || (this == buyingPrice));
    }


    @Override
    public String toString() {
        return csvValue;
    }
}
