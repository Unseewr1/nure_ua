package ua.nure.mykytchuk.ml.lw2.hypothesys.count;

import org.springframework.stereotype.Service;
import ua.nure.mykytchuk.ml.lw2.dom.car.price.BuyingPrice;
import ua.nure.mykytchuk.ml.lw2.dom.car.price.MaintenancePrice;
import ua.nure.mykytchuk.ml.lw2.dom.car.tech.Safety;
import ua.nure.mykytchuk.ml.lw2.dom.car.tech.comfort.DoorCount;
import ua.nure.mykytchuk.ml.lw2.dom.car.tech.comfort.LuggageBootSize;
import ua.nure.mykytchuk.ml.lw2.dom.car.tech.comfort.PersonCount;

@Service
public class CarHypothesisCounter {

    public int count() {
        return (BuyingPrice.values().length
                * MaintenancePrice.values().length
                * DoorCount.values().length
                * PersonCount.values().length
                * LuggageBootSize.values().length
                * Safety.values().length);
    }
}
