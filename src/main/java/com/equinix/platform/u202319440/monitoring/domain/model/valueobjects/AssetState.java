package com.equinix.platform.u202319440.monitoring.domain.model.valueobjects;

/**
 * Operational state for an asset record.
 */
public enum AssetState {
    OPERATING,
    IDLE;

    /**
     * Parses an asset state from its string representation.
     *
     * @param value asset state name
     * @return parsed asset state
     */
    public static AssetState fromValue(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("asset.record.error.assetState.invalid");
        }
        try {
            return AssetState.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("asset.record.error.assetState.invalid");
        }
    }
}
