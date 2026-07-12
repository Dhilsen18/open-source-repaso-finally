package com.equinix.platform.u202319440.monitoring.application.commandservices;

import com.equinix.platform.u202319440.monitoring.domain.model.aggregates.AssetRecord;
import com.equinix.platform.u202319440.monitoring.domain.model.commands.CreateAssetRecordCommand;
import com.equinix.platform.u202319440.shared.application.result.Result;

/**
 * Command service contract for asset record write operations.
 */
public interface AssetRecordCommandService {

    Result<AssetRecord, AssetRecordCommandFailure> handle(CreateAssetRecordCommand command);
}
