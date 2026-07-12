package com.equinix.platform.u202319440.elite.application.internal.commandservices;

import com.equinix.platform.u202319440.elite.application.commandservices.AssetCommandService;
import com.equinix.platform.u202319440.elite.infrastructure.persistence.jpa.repositories.AssetRepository;
import com.equinix.platform.u202319440.shared.domain.model.valueobjects.AssetTag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Application service implementing asset command operations.
 */
@Slf4j
@Service
public class AssetCommandServiceImpl implements AssetCommandService {
    private final AssetRepository assetRepository;

    public AssetCommandServiceImpl(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updatePreferredTemperature(AssetTag assetTag, Double targetTemperature) {
        assetRepository.findByAssetTag(assetTag).ifPresent(asset -> {
            if (!asset.getPreferredTemperature().equals(targetTemperature)) {
                asset.updatePreferredTemperature(targetTemperature);
                assetRepository.saveAndFlush(asset);
                log.info("Updated preferredTemperature for assetTag={} to {}", assetTag.value(), targetTemperature);
            }
        });
    }
}
