package com.miltonlara.JuegodelAhorcado;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JuegodelAhorcadoApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(JuegodelAhorcadoApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        System.out.println("El api esta funcionado pejeto");
    }
}
