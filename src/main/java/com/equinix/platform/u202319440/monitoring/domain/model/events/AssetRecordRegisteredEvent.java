package com.equinix.platform.u202319440.monitoring.domain.model.events;

import com.equinix.platform.u202319440.shared.domain.model.valueobjects.AssetTag;

/**
 * Integration event emitted when an asset record is successfully registered.
 */
public record AssetRecordRegisteredEvent(AssetTag assetTag, Double targetTemperature) {
}
