package ua.nure.mykytchuk.ml.lw2.dom.car;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ua.nure.mykytchuk.ml.lw2.dom.car.price.BuyingPrice;
import ua.nure.mykytchuk.ml.lw2.dom.car.price.MaintenancePrice;
import ua.nure.mykytchuk.ml.lw2.dom.car.tech.Safety;
import ua.nure.mykytchuk.ml.lw2.dom.car.tech.comfort.DoorCount;
import ua.nure.mykytchuk.ml.lw2.dom.car.tech.comfort.LuggageBootSize;
import ua.nure.mykytchuk.ml.lw2.dom.car.tech.comfort.PersonCount;

@Data
public class Car {

    @JsonProperty(
            value = "buying",
            index = 0)
    private BuyingPrice buyingPrice;

    @JsonProperty(
            value = "maint",
            index = 1)
    private MaintenancePrice maintenancePrice;

    @JsonProperty(
            value = "doors",
            index = 2)
    private DoorCount doorCount;

    @JsonProperty(
            value = "persons",
            index = 3)
    private PersonCount personCount;

    @JsonProperty(
            value = "lug_boot",
            index = 4)
    private LuggageBootSize luggageBootSize;

    @JsonProperty(
            value = "safety",
            index = 5)
    private Safety safety;

    @JsonProperty(
            value = "class",
            index = 6)
    private CarClass carClass;


    public Car() {
        buyingPrice = BuyingPrice.UNKNOWN;
        maintenancePrice = MaintenancePrice.UNKNOWN;
        doorCount = DoorCount.UNKNOWN;
        personCount = PersonCount.UNKNOWN;
        luggageBootSize = LuggageBootSize.UNKNOWN;
        safety = Safety.UNKNOWN;
    }
}
