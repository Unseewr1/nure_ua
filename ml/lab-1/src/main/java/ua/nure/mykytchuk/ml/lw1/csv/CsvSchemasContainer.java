package ua.nure.mykytchuk.ml.lw1.csv;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.nure.mykytchuk.ml.lw1.dom.Iris;

@Service
public class CsvSchemasContainer {

    private final char irisColumnSeparator;


    @Autowired
    public CsvSchemasContainer(@Value("${iris.column.separator}") char irisColumnSeparator) {
        this.irisColumnSeparator = irisColumnSeparator;
    }


    public CsvSchema getIrisCsvSchema(final @NonNull CsvMapper mapper) {
        return mapper.typedSchemaFor(Iris.class)
                .withColumnSeparator(irisColumnSeparator)
                .withoutHeader();
    }
}
