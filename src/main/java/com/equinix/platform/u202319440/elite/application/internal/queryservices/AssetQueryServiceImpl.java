package com.equinix.platform.u202319440.elite.application.internal.queryservices;

import com.equinix.platform.u202319440.elite.application.queryservices.AssetQueryService;
import com.equinix.platform.u202319440.elite.domain.model.aggregates.Asset;
import com.equinix.platform.u202319440.elite.infrastructure.persistence.jpa.repositories.AssetRepository;
import com.equinix.platform.u202319440.shared.domain.model.valueobjects.AssetTag;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Application service implementing asset query operations.
 */
@Service
@Transactional(readOnly = true)
public class AssetQueryServiceImpl implements AssetQueryService {
    private final AssetRepository assetRepository;

    public AssetQueryServiceImpl(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @Override
    public List<Asset> handle() {
        return assetRepository.findAll();
    }

    @Override
    public Optional<Asset> findByAssetTag(AssetTag assetTag) {
        return assetRepository.findByAssetTag(assetTag);
    }

    @Override
    public boolean existsByAssetTag(AssetTag assetTag) {
        return assetRepository.existsByAssetTag(assetTag);
    }
}
