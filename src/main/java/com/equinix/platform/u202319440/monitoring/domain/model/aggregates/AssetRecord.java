package com.equinix.platform.u202319440.monitoring.domain.model.aggregates;

import com.equinix.platform.u202319440.monitoring.domain.model.commands.CreateAssetRecordCommand;
import com.equinix.platform.u202319440.monitoring.domain.model.valueobjects.AssetState;
import com.equinix.platform.u202319440.monitoring.domain.model.valueobjects.OperationMode;
import com.equinix.platform.u202319440.shared.domain.model.valueobjects.AssetTag;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
 * Aggregate root for asset records in the monitoring bounded context.
 */
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class AssetRecord extends AbstractAggregateRoot<AssetRecord> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private AssetTag assetTag;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private OperationMode operationMode;

    @Column(nullable = false)
    private Double targetTemperature;

    @Column(nullable = false)
    private Double currentTemperature;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private AssetState assetState;

    @Column(nullable = false)
    private LocalDateTime generatedAt;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    protected AssetRecord() {
    }

    /**
     * Creates an asset record from a valid command.
     *
     * @param command create asset record command
     */
    public AssetRecord(CreateAssetRecordCommand command) {
        this.assetTag = command.assetTag();
        this.operationMode = command.operationMode();
        validateTargetTemperature(command.targetTemperature());
        this.targetTemperature = command.targetTemperature();
        validateCurrentTemperature(command.currentTemperature());
        this.currentTemperature = command.currentTemperature();
        this.assetState = command.assetState();
        validateGeneratedAt(command.generatedAt());
        this.generatedAt = command.generatedAt();
    }

    private void validateTargetTemperature(Double targetTemperature) {
        if (targetTemperature == null || targetTemperature <= 0) {
            throw new IllegalArgumentException("asset.record.error.targetTemperature.positive");
        }
        if (targetTemperature < 18.0 || targetTemperature > 27.0) {
            throw new IllegalArgumentException("asset.record.error.targetTemperature.range");
        }
    }

    private void validateCurrentTemperature(Double currentTemperature) {
        if (currentTemperature == null || currentTemperature <= 0) {
            throw new IllegalArgumentException("asset.record.error.currentTemperature.positive");
        }
    }

    private void validateGeneratedAt(LocalDateTime generatedAt) {
        if (generatedAt == null) {
            throw new IllegalArgumentException("asset.record.error.generatedAt.invalid");
        }
        if (generatedAt.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("asset.record.error.generatedAt.future");
        }
    }
}
