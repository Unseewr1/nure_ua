package ua.nure.mykytchuk.ml.lw2.dom.car;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter(onMethod = @__(@JsonValue))
public enum CarClass {

    UNACC("unacc"),
    ACC("acc"),
    GOOD("good"),
    VERY_GOOD("vgood");

    @NonNull
    private final String csvValue;


    @Override
    public String toString() {
        return csvValue;
    }
}
