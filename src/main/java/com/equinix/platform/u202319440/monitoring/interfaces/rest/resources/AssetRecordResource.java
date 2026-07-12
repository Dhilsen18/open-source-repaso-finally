package com.equinix.platform.u202319440.monitoring.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * REST resource representing an asset record.
 */
public record AssetRecordResource(
        @Schema(example = "1") Long id,
        @Schema(example = "SRV-EQX-SV-0001") String assetTag,
        @Schema(example = "ACTIVE") String operationMode,
        @Schema(example = "22.5") Double targetTemperature,
        @Schema(example = "23.0") Double currentTemperature,
        @Schema(example = "OPERATING") String assetState,
        @Schema(example = "2026-07-12 10:30:00") String generatedAt
) {
}
