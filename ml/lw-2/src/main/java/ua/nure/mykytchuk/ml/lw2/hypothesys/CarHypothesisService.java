package ua.nure.mykytchuk.ml.lw2.hypothesys;

import lombok.NonNull;
import ua.nure.mykytchuk.ml.lw2.dom.car.Car;

public interface CarHypothesisService {

    int INITIAL_STEP_INDEX = 1;

    default @NonNull String formatted(@NonNull Car hypothesisCar) {
        return "Car(%s, %s, %s, %s, %s)".formatted(
                hypothesisCar.getBuyingPrice(),
                hypothesisCar.getMaintenancePrice(),
                hypothesisCar.getDoorCount(),
                hypothesisCar.getLuggageBootSize(),
                hypothesisCar.getSafety()
        );
    }
}
