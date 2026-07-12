package com.equinix.platform.u202319440.monitoring.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * REST resource for creating an asset record.
 */
public record CreateAssetRecordResource(
        @NotBlank
        @Schema(example = "SRV-EQX-SV-0001")
        String assetTag,
        @NotBlank
        @Schema(example = "ACTIVE")
        String operationMode,
        @NotNull
        @Positive
        @Schema(example = "22.5")
        Double targetTemperature,
        @NotNull
        @Positive
        @Schema(example = "23.0")
        Double currentTemperature,
        @NotBlank
        @Schema(example = "OPERATING")
        String assetState,
        @NotBlank
        @Schema(example = "2026-07-12 10:30:00")
        String generatedAt
) {
}
