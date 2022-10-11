package ua.nure.mykytchuk.ml.lw2.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "ua.nure.mykytchuk.ml.lw2")
public class Lw2Application {

    public static void main(String[] args) {
        SpringApplication.run(Lw2Application.class, args);
    }
}
