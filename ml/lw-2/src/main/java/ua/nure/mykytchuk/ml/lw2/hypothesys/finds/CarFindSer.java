package ua.nure.mykytchuk.ml.lw2.hypothesys.finds;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ua.nure.mykytchuk.ml.lw2.dom.car.Car;
import ua.nure.mykytchuk.ml.lw2.dom.car.price.BuyingPrice;
import ua.nure.mykytchuk.ml.lw2.dom.car.price.MaintenancePrice;
import ua.nure.mykytchuk.ml.lw2.dom.car.tech.Safety;
import ua.nure.mykytchuk.ml.lw2.dom.car.tech.comfort.DoorCount;
import ua.nure.mykytchuk.ml.lw2.dom.car.tech.comfort.LuggageBootSize;
import ua.nure.mykytchuk.ml.lw2.dom.car.tech.comfort.PersonCount;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CarFindSer {

    private final @NonNull Car hypothesisCar;


    public static @NonNull CarFindSer of(@NonNull Car hypothesisCar) {
        return new CarFindSer(hypothesisCar);
    }


    public boolean anyOfFieldsIsKnown() {
        return (hypothesisCar.getBuyingPrice().isKnown()
                || hypothesisCar.getMaintenancePrice().isKnown()
                || hypothesisCar.getDoorCount().isKnown()
                || hypothesisCar.getPersonCount().isKnown()
                || hypothesisCar.getLuggageBootSize().isKnown()
                || hypothesisCar.getSafety().isKnown());
    }

    public @NonNull Car checkHypothesis(@NonNull Car car) {
        if (hypothesisCar.getBuyingPrice() != car.getBuyingPrice()) {
            hypothesisCar.setBuyingPrice(BuyingPrice.UNKNOWN);
        }
        if (hypothesisCar.getMaintenancePrice() != car.getMaintenancePrice()) {
            hypothesisCar.setMaintenancePrice(MaintenancePrice.UNKNOWN);
        }
        if (hypothesisCar.getDoorCount() != car.getDoorCount()) {
            hypothesisCar.setDoorCount(DoorCount.UNKNOWN);
        }
        if (hypothesisCar.getPersonCount() != car.getPersonCount()) {
            hypothesisCar.setPersonCount(PersonCount.UNKNOWN);
        }
        if (hypothesisCar.getLuggageBootSize() != car.getLuggageBootSize()) {
            hypothesisCar.setLuggageBootSize(LuggageBootSize.UNKNOWN);
        }
        if (hypothesisCar.getSafety() != car.getSafety()) {
            hypothesisCar.setSafety(Safety.UNKNOWN);
        }
        return hypothesisCar;
    }
}

