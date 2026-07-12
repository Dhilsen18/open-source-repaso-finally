package com.equinix.platform.u202319440.monitoring.infrastructure.acl;

import com.equinix.platform.u202319440.elite.application.queryservices.AssetQueryService;
import com.equinix.platform.u202319440.shared.domain.model.valueobjects.AssetTag;
import org.springframework.stereotype.Component;

/**
 * ACL implementation delegating asset lookups to the elite bounded context.
 */
@Component
public class EliteAssetContextFacadeImpl implements EliteAssetContextFacade {
    private final AssetQueryService assetQueryService;

    public EliteAssetContextFacadeImpl(AssetQueryService assetQueryService) {
        this.assetQueryService = assetQueryService;
    }

    @Override
    public boolean existsByAssetTag(AssetTag assetTag) {
        return assetQueryService.existsByAssetTag(assetTag);
    }
}
