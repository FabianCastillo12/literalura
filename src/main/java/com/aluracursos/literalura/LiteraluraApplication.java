package com.aluracursos.literalura;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aluracursos.literalura.Principal.AppPrincipal;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

    private final AppPrincipal appPrincipal;

    public LiteraluraApplication(AppPrincipal appPrincipal) {
        this.appPrincipal = appPrincipal;
    }

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        appPrincipal.mostrarMenu();
    }
}

