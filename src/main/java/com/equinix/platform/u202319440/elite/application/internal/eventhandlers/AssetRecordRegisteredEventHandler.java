package com.equinix.platform.u202319440.elite.application.internal.eventhandlers;

import com.equinix.platform.u202319440.elite.application.commandservices.AssetCommandService;
import com.equinix.platform.u202319440.monitoring.domain.model.events.AssetRecordRegisteredEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * Handles integration events from the monitoring bounded context.
 */
@Component
public class AssetRecordRegisteredEventHandler {
    private final AssetCommandService assetCommandService;

    public AssetRecordRegisteredEventHandler(AssetCommandService assetCommandService) {
        this.assetCommandService = assetCommandService;
    }

    /**
     * Updates the asset preferred temperature when a new record is registered.
     *
     * @param event integration event published by monitoring
     */
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void on(AssetRecordRegisteredEvent event) {
        assetCommandService.updatePreferredTemperature(event.assetTag(), event.targetTemperature());
    }
}
