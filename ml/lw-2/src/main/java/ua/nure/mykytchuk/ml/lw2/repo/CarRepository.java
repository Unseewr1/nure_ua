package ua.nure.mykytchuk.ml.lw2.repo;

import com.fasterxml.jackson.databind.MappingIterator;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ua.nure.mykytchuk.ml.lw2.csv.schema.CarCsvMapper;
import ua.nure.mykytchuk.ml.lw2.dom.car.Car;
import ua.nure.mykytchuk.ml.lw2.dom.car.CarClass;
import ua.nure.mykytchuk.ml.lw2.exception.DataSourceCantBeOpenException;
import ua.nure.mykytchuk.ml.lw2.exception.IllegalDataFormatException;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class CarRepository {

    private final @NonNull String carDataFilename;
    private final @NonNull CarCsvMapper carCsvMapper;


    @Autowired
    public CarRepository(
            @Value("${car.data.filename}") String carDataFilename,
            CarCsvMapper carCsvMapper
    ) {
        this.carDataFilename = carDataFilename;
        this.carCsvMapper = carCsvMapper;
    }


    public @NonNull List<Car> findAll() {
        try (MappingIterator<Car> carMappingIterator = getCarMappingIterator(
                carCsvMapper,
                carDataFilename
        )) {
            return carMappingIterator.readAll();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new IllegalDataFormatException();
        }
    }

    public @NonNull List<Car> findByCarClass(@NonNull CarClass carClass) {
        return findAll().stream()
                .filter(car -> carClass == car.getCarClass())
                .toList();
    }


    private @NonNull MappingIterator<Car> getCarMappingIterator(
            @NonNull CarCsvMapper carCsvMapper,
            @NonNull String carDataFilename
    ) throws IOException {
        return carCsvMapper
                .getCarReader()
                .readValues(getCarDataResource(carDataFilename));
    }

    private @NonNull URL getCarDataResource(
            @NonNull String carDataFilename
    ) {
        return Optional.ofNullable(CarRepository.class.getClassLoader()
                        .getResource(carDataFilename))
                .orElseThrow(DataSourceCantBeOpenException::new);
    }
}
