package com.equinix.platform.u202319440.monitoring.interfaces.rest.transform;

import com.equinix.platform.u202319440.monitoring.domain.model.aggregates.AssetRecord;
import com.equinix.platform.u202319440.monitoring.interfaces.rest.resources.AssetRecordResource;

import java.time.format.DateTimeFormatter;

/**
 * Assembler for mapping asset record entities to REST resources.
 */
public class AssetRecordResourceFromEntityAssembler {
    private static final DateTimeFormatter GENERATED_AT_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private AssetRecordResourceFromEntityAssembler() {
    }

    /**
     * Maps an asset record entity to its REST resource.
     *
     * @param assetRecord asset record entity
     * @return asset record resource
     */
    public static AssetRecordResource toResourceFromEntity(AssetRecord assetRecord) {
        return new AssetRecordResource(
                assetRecord.getId(),
                assetRecord.getAssetTag().value(),
                assetRecord.getOperationMode().name(),
                assetRecord.getTargetTemperature(),
                assetRecord.getCurrentTemperature(),
                assetRecord.getAssetState().name(),
                assetRecord.getGeneratedAt().format(GENERATED_AT_FORMATTER)
        );
    }
}
