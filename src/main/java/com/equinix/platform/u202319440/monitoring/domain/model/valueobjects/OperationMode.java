package com.equinix.platform.u202319440.monitoring.domain.model.valueobjects;

/**
 * Operation mode for an asset record.
 */
public enum OperationMode {
    STAND_BY,
    ACTIVE;

    /**
     * Parses an operation mode from its string representation.
     *
     * @param value operation mode name
     * @return parsed operation mode
     */
    public static OperationMode fromValue(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("asset.record.error.operationMode.invalid");
        }
        try {
            return OperationMode.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("asset.record.error.operationMode.invalid");
        }
    }
}
