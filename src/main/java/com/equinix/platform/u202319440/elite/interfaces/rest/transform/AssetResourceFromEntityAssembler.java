package com.equinix.platform.u202319440.elite.interfaces.rest.transform;

import com.equinix.platform.u202319440.elite.domain.model.aggregates.Asset;
import com.equinix.platform.u202319440.elite.interfaces.rest.resources.AssetResource;

import java.util.List;

/**
 * Assembler for mapping asset entities to REST resources.
 */
public class AssetResourceFromEntityAssembler {

    private AssetResourceFromEntityAssembler() {
    }

    /**
     * Maps an asset entity to its REST resource.
     *
     * @param asset asset entity
     * @return asset resource
     */
    public static AssetResource toResourceFromEntity(Asset asset) {
        return new AssetResource(
                asset.getId(),
                asset.getAssetTag().value(),
                asset.getRoomId(),
                asset.getPreferredTemperature()
        );
    }

    /**
     * Maps a list of asset entities to REST resources.
     *
     * @param assets asset entities
     * @return asset resources
     */
    public static List<AssetResource> toResourceListFromEntityList(List<Asset> assets) {
        return assets.stream()
                .map(AssetResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
    }
}
