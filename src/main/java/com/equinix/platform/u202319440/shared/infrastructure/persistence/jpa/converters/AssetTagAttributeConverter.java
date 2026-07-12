package com.equinix.platform.u202319440.shared.infrastructure.persistence.jpa.converters;

import com.equinix.platform.u202319440.shared.domain.model.valueobjects.AssetTag;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * JPA attribute converter for {@link AssetTag}.
 */
@Converter(autoApply = true)
public class AssetTagAttributeConverter implements AttributeConverter<AssetTag, String> {

    @Override
    public String convertToDatabaseColumn(AssetTag assetTag) {
        return assetTag == null ? null : assetTag.value();
    }

    @Override
    public AssetTag convertToEntityAttribute(String value) {
        return value == null ? null : new AssetTag(value);
    }
}
