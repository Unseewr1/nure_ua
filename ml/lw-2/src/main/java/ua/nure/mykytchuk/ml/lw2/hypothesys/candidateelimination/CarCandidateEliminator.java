package ua.nure.mykytchuk.ml.lw2.hypothesys.candidateelimination;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ua.nure.mykytchuk.ml.lw2.dom.car.Car;
import ua.nure.mykytchuk.ml.lw2.dom.car.CarClass;
import ua.nure.mykytchuk.ml.lw2.hypothesys.finds.CarFindSer;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CarCandidateEliminator {

    @NonNull
    private final CarClass carClass;

    @NonNull
    private Car sCar = new Car();

    @NonNull
    private final Set<Car> gSet = new HashSet<>();


    public static @NonNull CarCandidateEliminator of(
            @NonNull CarClass carClass
    ) {
        return new CarCandidateEliminator(carClass);
    }


    public void eliminate(@NonNull Car car) {
        if ((sCar.getCarClass() == null) && (carClass == car.getCarClass())) {
            sCar = car;
            return;
        }
        //if hypothesis is possible
        if (isPositive(car)) {
            CarFindSer.of(sCar).checkHypothesis(car);
            return;
        }
        if (sCar.getBuyingPrice() != car.getBuyingPrice()) {
            Car gCar = new Car();
            gCar.setBuyingPrice(sCar.getBuyingPrice());
            gSet.add(gCar);
        }
        if (sCar.getMaintenancePrice() != car.getMaintenancePrice()) {
            Car gCar = new Car();
            gCar.setMaintenancePrice(sCar.getMaintenancePrice());
            gSet.add(gCar);
        }
        if (sCar.getDoorCount() != car.getDoorCount()) {
            Car gCar = new Car();
            gCar.setDoorCount(sCar.getDoorCount());
            gSet.add(gCar);
        }
        if (sCar.getPersonCount() != car.getPersonCount()) {
            Car gCar = new Car();
            gCar.setPersonCount(sCar.getPersonCount());
            gSet.add(gCar);
        }
        if (sCar.getLuggageBootSize() != car.getLuggageBootSize()) {
            Car gCar = new Car();
            gCar.setLuggageBootSize(sCar.getLuggageBootSize());
            gSet.add(gCar);
        }
        if (sCar.getSafety() != car.getSafety()) {
            Car gCar = new Car();
            gCar.setSafety(sCar.getSafety());
            gSet.add(gCar);
        }
        updateGSet();
    }

    private void updateGSet() {
        gSet.removeIf(car -> correlatesTo(car, sCar));
    }

    private boolean correlatesTo(
            @NonNull Car car,
            @NonNull Car sCar
    ) {
        return (car.getBuyingPrice().correlatesTo(sCar.getBuyingPrice())
                && car.getMaintenancePrice().correlatesTo(sCar.getMaintenancePrice())
                && car.getDoorCount().correlatesTo(sCar.getDoorCount())
                && car.getPersonCount().correlatesTo(sCar.getPersonCount())
                && car.getLuggageBootSize().correlatesTo(sCar.getLuggageBootSize())
                && car.getSafety().correlatesTo(sCar.getSafety()));
    }

    public boolean canChangeResult() {
        return (sCar.getCarClass() == null) || (sCar.getBuyingPrice().isKnown()
                || sCar.getMaintenancePrice().isKnown()
                || sCar.getDoorCount().isKnown()
                || sCar.getPersonCount().isKnown()
                || sCar.getLuggageBootSize().isKnown()
                || sCar.getSafety().isKnown());
    }

    public boolean isPositive(@NonNull Car car) {
        return (sCar.getCarClass() == car.getCarClass());
    }
}