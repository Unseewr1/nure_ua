package ua.nure.mykytchuk.ml.lw2.formatting;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import ua.nure.mykytchuk.ml.lw2.dom.car.Car;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CarFormatService {

    private static final String CAR_FORMAT = "Car(%s, %s, %s, %s, %s, %s)";
    private static final String CAR_LIST_DELIMITER = ", ";


    public @NonNull String format(@NonNull Car hypothesisCar) {
        return CAR_FORMAT.formatted(
                hypothesisCar.getBuyingPrice(),
                hypothesisCar.getMaintenancePrice(),
                hypothesisCar.getDoorCount(),
                hypothesisCar.getPersonCount(),
                hypothesisCar.getLuggageBootSize(),
                hypothesisCar.getSafety()
        );
    }

    public @NonNull String format(@NonNull Collection<@NonNull Car> cars) {
        return cars.stream()
                .map(this::format)
                .collect(Collectors.joining(CAR_LIST_DELIMITER));
    }
}
