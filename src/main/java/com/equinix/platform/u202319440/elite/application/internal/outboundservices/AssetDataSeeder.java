package com.equinix.platform.u202319440.elite.application.internal.outboundservices;

import com.equinix.platform.u202319440.elite.domain.model.aggregates.Asset;
import com.equinix.platform.u202319440.elite.infrastructure.persistence.jpa.repositories.AssetRepository;
import com.equinix.platform.u202319440.shared.domain.model.valueobjects.AssetTag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Seeds initial asset data when the application is ready.
 *
 * @author Dhilsen Armil Mallqui Vilca
 */
@Slf4j
@Component
public class AssetDataSeeder {
    private final AssetRepository assetRepository;

    public AssetDataSeeder(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void onApplicationReady() {
        if (assetRepository.count() > 0) {
            return;
        }
        seedAsset("SRV-EQX-SV-0001", 12L, 22.0);
        seedAsset("CRAC-EQX-AC-0103", 5L, 20.0);
        seedAsset("UPS-EQX-UP-0201", 9L, 23.5);
        seedAsset("PDU-EQX-PD-0310", 5L, 21.0);
        log.info("Seeded {} assets", assetRepository.count());
    }

    private void seedAsset(String assetTagValue, Long roomId, Double preferredTemperature) {
        Asset asset = new Asset(new AssetTag(assetTagValue), roomId, preferredTemperature);
        assetRepository.save(asset);
    }
}
