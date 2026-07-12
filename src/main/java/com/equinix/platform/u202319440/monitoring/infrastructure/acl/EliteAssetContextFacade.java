package com.equinix.platform.u202319440.monitoring.infrastructure.acl;

import com.equinix.platform.u202319440.shared.domain.model.valueobjects.AssetTag;

/**
 * Anti-corruption layer facade exposing elite asset capabilities to monitoring.
 */
public interface EliteAssetContextFacade {

    boolean existsByAssetTag(AssetTag assetTag);
}
