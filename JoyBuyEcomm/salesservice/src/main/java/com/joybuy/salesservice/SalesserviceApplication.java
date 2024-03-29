package com.joybuy.salesservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class SalesserviceApplication
{
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }
    public static void main(String[] args) {
        SpringApplication.run(SalesserviceApplication.class, args);
    }

}
