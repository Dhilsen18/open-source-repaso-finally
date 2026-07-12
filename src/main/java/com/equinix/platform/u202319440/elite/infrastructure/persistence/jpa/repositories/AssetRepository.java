package com.equinix.platform.u202319440.elite.infrastructure.persistence.jpa.repositories;

import com.equinix.platform.u202319440.elite.domain.model.aggregates.Asset;
import com.equinix.platform.u202319440.shared.domain.model.valueobjects.AssetTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * JPA repository for {@link Asset} aggregate persistence.
 */
public interface AssetRepository extends JpaRepository<Asset, Long> {

    boolean existsByAssetTag(AssetTag assetTag);

    Optional<Asset> findByAssetTag(AssetTag assetTag);
}
