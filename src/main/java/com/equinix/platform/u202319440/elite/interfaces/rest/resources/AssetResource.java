package com.equinix.platform.u202319440.elite.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * REST resource representing an asset.
 */
public record AssetResource(
        @Schema(example = "1") Long id,
        @Schema(example = "SRV-EQX-SV-0001") String assetTag,
        @Schema(example = "12") Long roomId,
        @Schema(example = "22.0") Double preferredTemperature
) {
}
