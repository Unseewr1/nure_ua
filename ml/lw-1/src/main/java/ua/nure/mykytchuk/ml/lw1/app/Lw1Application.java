package ua.nure.mykytchuk.ml.lw1.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "ua.nure.mykytchuk.ml.lw1")
public class Lw1Application {

    public static void main(
            String[] args
    ) {
        SpringApplication.run(Lw1Application.class, args);
    }
}
