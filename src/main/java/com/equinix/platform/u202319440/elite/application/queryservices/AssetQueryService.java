package com.equinix.platform.u202319440.elite.application.queryservices;

import com.equinix.platform.u202319440.elite.domain.model.aggregates.Asset;
import com.equinix.platform.u202319440.shared.domain.model.valueobjects.AssetTag;

import java.util.List;
import java.util.Optional;

/**
 * Query service contract for asset read operations.
 */
public interface AssetQueryService {

    List<Asset> handle();

    Optional<Asset> findByAssetTag(AssetTag assetTag);

    boolean existsByAssetTag(AssetTag assetTag);
}
