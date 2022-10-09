package ua.nure.mykytchuk.ml.lw1.dom;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import ua.nure.mykytchuk.ml.lw1.commons.DoubleDeserializer;

@Data
public class Iris {

    @JsonDeserialize(using = DoubleDeserializer.class)
    @JsonProperty(index = 0)
    private double sepalLength;
    @JsonDeserialize(using = DoubleDeserializer.class)
    @JsonProperty(index = 1)
    private double sepalWidth;
    @JsonDeserialize(using = DoubleDeserializer.class)
    @JsonProperty(index = 2)
    private double petalLength;
    @JsonDeserialize(using = DoubleDeserializer.class)
    @JsonProperty(index = 3)
    private double petalWidth;
    @JsonProperty(index = 4)
    private String aClass;
}
