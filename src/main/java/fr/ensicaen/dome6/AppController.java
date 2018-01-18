package fr.ensicaen.dome6;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.*;

@SpringBootApplication
@Controller
@EnableAutoConfiguration
public class AppController {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AppController.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}