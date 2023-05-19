package com.sonu.kafka.Validators;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigException;

import java.time.Instant;
import java.time.format.DateTimeParseException;

public class TimeStampValidator implements ConfigDef.Validator {
    @Override
    public void ensureValid(String name, Object value) {
        String timestamp = (String) value;
        try {
            Instant.parse(timestamp);

        }catch (DateTimeParseException e) {
            throw new ConfigException(name, value,
                    "failed to parse timestamp, ensure it is in ISO-8601 Standard format YYYY-MM-DDTHH:MM:SSZ");
        }
    }
}
