package com.equinix.platform.u202319440.monitoring.infrastructure.persistence.jpa.repositories;

import com.equinix.platform.u202319440.monitoring.domain.model.aggregates.AssetRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA repository for {@link AssetRecord} aggregate persistence.
 */
public interface AssetRecordRepository extends JpaRepository<AssetRecord, Long> {
}
