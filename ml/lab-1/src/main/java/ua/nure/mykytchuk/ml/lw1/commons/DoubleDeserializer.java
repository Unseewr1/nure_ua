package ua.nure.mykytchuk.ml.lw1.commons;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class DoubleDeserializer extends StdDeserializer<Double> {

    @SuppressWarnings("unused")
    public DoubleDeserializer() {
        super((Class<?>) null);
    }

    @SuppressWarnings("unused")
    public DoubleDeserializer(Class<Double> vc) {
        super(vc);
    }


    @Override
    public Double deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        return Double.valueOf(context.readValue(jsonParser, String.class));
    }
}
