package com.lseg_proy.accountsystem;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "Account System", version = "v1", description = "Documentation of Account System V1"))
@SpringBootApplication
public class AccountSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountSystemApplication.class, args);
    }

}
