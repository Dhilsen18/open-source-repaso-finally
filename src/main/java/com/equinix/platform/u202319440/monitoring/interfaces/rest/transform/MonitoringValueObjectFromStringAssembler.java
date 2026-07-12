package com.equinix.platform.u202319440.monitoring.interfaces.rest.transform;

import com.equinix.platform.u202319440.monitoring.domain.model.valueobjects.AssetState;
import com.equinix.platform.u202319440.monitoring.domain.model.valueobjects.OperationMode;
import com.equinix.platform.u202319440.shared.domain.model.valueobjects.AssetTag;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Assembler for parsing monitoring value objects from string representations.
 */
public class MonitoringValueObjectFromStringAssembler {
    private static final DateTimeFormatter GENERATED_AT_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private MonitoringValueObjectFromStringAssembler() {
    }

    /**
     * Parses an asset tag from a string value.
     *
     * @param value raw asset tag
     * @return asset tag value object
     */
    public static AssetTag toAssetTagFromString(String value) {
        return new AssetTag(value);
    }

    /**
     * Parses an operation mode from a string value.
     *
     * @param value raw operation mode
     * @return operation mode enum
     */
    public static OperationMode toOperationModeFromString(String value) {
        return OperationMode.fromValue(value);
    }

    /**
     * Parses an asset state from a string value.
     *
     * @param value raw asset state
     * @return asset state enum
     */
    public static AssetState toAssetStateFromString(String value) {
        return AssetState.fromValue(value);
    }

    /**
     * Parses a generated-at timestamp from a string value.
     *
     * @param value raw generated-at value
     * @return parsed local date time
     */
    public static LocalDateTime toGeneratedAtFromString(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("asset.record.error.generatedAt.invalid");
        }
        try {
            return LocalDateTime.parse(value.trim(), GENERATED_AT_FORMATTER);
        } catch (DateTimeParseException exception) {
            throw new IllegalArgumentException("asset.record.error.generatedAt.invalid");
        }
    }
}
