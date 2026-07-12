package com.equinix.platform.u202319440.elite.application.commandservices;

import com.equinix.platform.u202319440.shared.domain.model.valueobjects.AssetTag;

/**
 * Command service contract for asset write operations.
 */
public interface AssetCommandService {

    void updatePreferredTemperature(AssetTag assetTag, Double targetTemperature);
}
