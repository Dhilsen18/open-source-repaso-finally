package com.equinix.platform.u202319440.monitoring.interfaces.rest.transform;

import com.equinix.platform.u202319440.monitoring.domain.model.commands.CreateAssetRecordCommand;
import com.equinix.platform.u202319440.monitoring.interfaces.rest.resources.CreateAssetRecordResource;

/**
 * Assembler for mapping create asset record resources to domain commands.
 */
public class CreateAssetRecordCommandFromResourceAssembler {

    private CreateAssetRecordCommandFromResourceAssembler() {
    }

    /**
     * Converts a create asset record resource to a domain command.
     *
     * @param resource inbound REST resource
     * @return create asset record command
     */
    public static CreateAssetRecordCommand toCommandFromResource(CreateAssetRecordResource resource) {
        return new CreateAssetRecordCommand(
                MonitoringValueObjectFromStringAssembler.toAssetTagFromString(resource.assetTag()),
                MonitoringValueObjectFromStringAssembler.toOperationModeFromString(resource.operationMode()),
                resource.targetTemperature(),
                resource.currentTemperature(),
                MonitoringValueObjectFromStringAssembler.toAssetStateFromString(resource.assetState()),
                MonitoringValueObjectFromStringAssembler.toGeneratedAtFromString(resource.generatedAt())
        );
    }
}
