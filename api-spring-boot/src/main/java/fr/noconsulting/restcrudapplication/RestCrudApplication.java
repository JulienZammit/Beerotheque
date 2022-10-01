/**
 * Nom de classe : RestCrudApplication
 * Description   : Application Spring Boot Main
 * Date          : 13/05/2022
 * Copyright     : ZAMMIT Julien
 */

package fr.noconsulting.restcrudapplication;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestCrudApplication {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(RestCrudApplication.class, args);
    }

}
