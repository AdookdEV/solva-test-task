package com.example.expensetrackerservice.dto;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TwelvedataCurrencyDTO {
    private TwelvedataCurrencyDTO.Meta meta;
    private List<TwelvedataCurrencyDTO.Value> values;

    public TwelvedataCurrencyDTO.Value getLastValue() {
        return values.get(0);
    }

    @Getter
    @Setter
    public static class Meta {
        private String symbol;
    }

    @Getter
    @Setter
    public static class Value {
        private ZonedDateTime datetime;
        private String close;

        public void setDatetime(LocalDate date) {
            var zoneId = ZoneId.of("America/New_York");
            this.datetime = ZonedDateTime.of(date, ZonedDateTime.now(zoneId).toLocalTime(), zoneId);
        }
    }
}
