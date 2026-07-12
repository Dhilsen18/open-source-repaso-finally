package com.equinix.platform.u202319440.elite.domain.model.aggregates;

import com.equinix.platform.u202319440.shared.domain.model.valueobjects.AssetTag;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * Aggregate root for assets managed in the elite bounded context.
 *
 * @author Dhilsen Armil Mallqui Vilca
 */
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Asset extends AbstractAggregateRoot<Asset> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private AssetTag assetTag;

    @Column(nullable = false)
    private Long roomId;

    @Column(nullable = false)
    private Double preferredTemperature;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    protected Asset() {
    }

    /**
     * Creates an asset aggregate with validated attributes.
     *
     * @param assetTag asset tag value object
     * @param roomId room identifier
     * @param preferredTemperature preferred temperature in Celsius
     */
    public Asset(AssetTag assetTag, Long roomId, Double preferredTemperature) {
        this.assetTag = assetTag;
        validateRoomId(roomId);
        this.roomId = roomId;
        validatePreferredTemperature(preferredTemperature);
        this.preferredTemperature = preferredTemperature;
    }

    /**
     * Updates the preferred temperature when a new target value is reported.
     *
     * @param targetTemperature new preferred temperature
     */
    public void updatePreferredTemperature(Double targetTemperature) {
        validatePreferredTemperature(targetTemperature);
        if (!this.preferredTemperature.equals(targetTemperature)) {
            this.preferredTemperature = targetTemperature;
        }
    }

    private void validateRoomId(Long roomId) {
        if (roomId == null || roomId <= 0) {
            throw new IllegalArgumentException("asset.error.roomId.positive");
        }
    }

    private void validatePreferredTemperature(Double preferredTemperature) {
        if (preferredTemperature == null || preferredTemperature <= 0) {
            throw new IllegalArgumentException("asset.error.preferredTemperature.positive");
        }
    }
}
