package com.equinix.platform.u202319440.monitoring.application.internal.commandservices;

import com.equinix.platform.u202319440.monitoring.application.commandservices.AssetRecordCommandFailure;
import com.equinix.platform.u202319440.monitoring.application.commandservices.AssetRecordCommandService;
import com.equinix.platform.u202319440.monitoring.domain.model.aggregates.AssetRecord;
import com.equinix.platform.u202319440.monitoring.domain.model.commands.CreateAssetRecordCommand;
import com.equinix.platform.u202319440.monitoring.infrastructure.acl.EliteAssetContextFacade;
import com.equinix.platform.u202319440.monitoring.infrastructure.persistence.jpa.repositories.AssetRecordRepository;
import com.equinix.platform.u202319440.shared.application.result.Result;
import lombok.extern.slf4j.Slf4j;
import com.equinix.platform.u202319440.monitoring.domain.model.events.AssetRecordRegisteredEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Application service implementing asset record command operations.
 */
@Slf4j
@Service
public class AssetRecordCommandServiceImpl implements AssetRecordCommandService {
    private final AssetRecordRepository assetRecordRepository;
    private final EliteAssetContextFacade eliteAssetContextFacade;
    private final ApplicationEventPublisher applicationEventPublisher;

    public AssetRecordCommandServiceImpl(
            AssetRecordRepository assetRecordRepository,
            EliteAssetContextFacade eliteAssetContextFacade,
            ApplicationEventPublisher applicationEventPublisher) {
        this.assetRecordRepository = assetRecordRepository;
        this.eliteAssetContextFacade = eliteAssetContextFacade;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    @Transactional
    public Result<AssetRecord, AssetRecordCommandFailure> handle(CreateAssetRecordCommand command) {
        if (!eliteAssetContextFacade.existsByAssetTag(command.assetTag())) {
            log.warn("Asset not found for assetTag={}", command.assetTag().value());
            return Result.failure(new AssetRecordCommandFailure.AssetNotFound());
        }
        var assetRecord = new AssetRecord(command);
        var createdAssetRecord = assetRecordRepository.save(assetRecord);
        applicationEventPublisher.publishEvent(
                new AssetRecordRegisteredEvent(command.assetTag(), command.targetTemperature()));
        log.info("Asset record created: id={}, assetTag={}", createdAssetRecord.getId(), command.assetTag().value());
        return Result.success(createdAssetRecord);
    }
}
