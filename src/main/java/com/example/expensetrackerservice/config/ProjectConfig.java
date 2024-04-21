package com.example.expensetrackerservice.config;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.expensetrackerservice.utils.CustomJsonDateDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;

@Configuration
public class ProjectConfig {
    @Value("${app.date-format}")
    private String datePattern;

    @Bean
    public com.fasterxml.jackson.databind.Module dynamoDemoEntityDeserializer() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(ZonedDateTime.class, new CustomJsonDateDeserializer(DateTimeFormatter.ofPattern(datePattern)));
        return module;
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> {
            builder.serializers(new ZonedDateTimeSerializer(DateTimeFormatter.ofPattern(datePattern)));
        };
    }
}
