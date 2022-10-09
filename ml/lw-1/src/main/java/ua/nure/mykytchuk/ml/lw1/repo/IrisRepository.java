package ua.nure.mykytchuk.ml.lw1.repo;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ua.nure.mykytchuk.ml.lw1.commons.RandomComparator;
import ua.nure.mykytchuk.ml.lw1.csv.CsvSchemasContainer;
import ua.nure.mykytchuk.ml.lw1.dom.Iris;
import ua.nure.mykytchuk.ml.lw1.exception.DataSourceCantBeOpenException;
import ua.nure.mykytchuk.ml.lw1.exception.EntityNotFoundException;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@Repository
public class IrisRepository {

    private final String irisDataFilename;

    private final @NonNull CsvSchemasContainer csvSchemasContainer;

    private final @NonNull List<Iris> irises;


    @Autowired
    public IrisRepository(@Value("${iris.data.filename}") String irisDataFilename,
                          CsvSchemasContainer csvSchemasContainer) {
        this.irisDataFilename = irisDataFilename;
        this.csvSchemasContainer = csvSchemasContainer;
        try (MappingIterator<Iris> iterator = getIterator()) {
            irises = iterator.readAll();
        } catch (IOException e) {
            throw new DataSourceCantBeOpenException();
        }
    }


    public @NonNull Iris getById(int id) {
        try {
            return irises.get(id);
        } catch (IndexOutOfBoundsException e) {
            throw new EntityNotFoundException();
        }
    }

    public int getIrisCount() {
        return irises.size();
    }


    public void sortRandomly() {
        while (true) {
            try {
                irises.sort(new RandomComparator<>());
                return;
            } catch (IllegalArgumentException ignored) {
                //need to try sorting again
            }
        }
    }

    public void replaceIrisesClassesWithIds() {
        IntStream.range(0, irises.size())
                .forEach(id -> irises.get(id).setAClass(String.valueOf(id)));
    }


    private @NonNull MappingIterator<Iris> getIterator() throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = csvSchemasContainer.getIrisCsvSchema(mapper);
        return mapper
                .readerFor(Iris.class)
                .with(schema)
                .readValues(IrisRepository.class.getClassLoader().getResource(irisDataFilename));
    }
}
