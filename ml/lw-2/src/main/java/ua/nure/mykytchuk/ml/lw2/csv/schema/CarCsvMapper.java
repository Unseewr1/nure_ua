package ua.nure.mykytchuk.ml.lw2.csv.schema;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.nure.mykytchuk.ml.lw2.dom.car.Car;

@Service
public class CarCsvMapper extends CsvMapper {

    @Getter
    private final ObjectReader carReader;


    @Autowired
    public CarCsvMapper(
            @Value("${car.data.filename}") String carDataFilename,
            @Value("${car.column.separator}") char carColumnSeparator
    ) {
        CsvSchema carCvsSchema = typedSchemaFor(Car.class)
                .withColumnSeparator(carColumnSeparator)
                .withoutHeader();
        this.carReader = readerFor(Car.class)
                .with(carCvsSchema);
    }
}
