package com.telerik.extension_repository;

import com.telerik.extension_repository.services.StorageProperties;
import com.telerik.extension_repository.services.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class ExtensionRepositoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExtensionRepositoryApplication.class, args);
    }

//    @Bean
//    CommandLineRunner init(StorageService storageService) {
//        return (args) -> {
//            storageService.deleteAll();
//            storageService.init();
//        };
//    }
}
