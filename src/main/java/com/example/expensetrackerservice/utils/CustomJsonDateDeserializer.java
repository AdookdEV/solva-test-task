package com.example.expensetrackerservice.utils;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CustomJsonDateDeserializer extends JsonDeserializer<ZonedDateTime> {
    private DateTimeFormatter formatter;

    @Override
    public ZonedDateTime deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JacksonException {
        String date = jsonParser.getText();
        return ZonedDateTime.parse(date, formatter);
    }
}