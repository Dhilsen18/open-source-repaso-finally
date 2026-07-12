package com.equinix.platform.u202319440.monitoring.domain.model.commands;

import com.equinix.platform.u202319440.monitoring.domain.model.valueobjects.AssetState;
import com.equinix.platform.u202319440.monitoring.domain.model.valueobjects.OperationMode;
import com.equinix.platform.u202319440.shared.domain.model.valueobjects.AssetTag;

import java.time.LocalDateTime;

/**
 * Command to create a new asset record.
 */
public record CreateAssetRecordCommand(
        AssetTag assetTag,
        OperationMode operationMode,
        Double targetTemperature,
        Double currentTemperature,
        AssetState assetState,
        LocalDateTime generatedAt
) {
}
