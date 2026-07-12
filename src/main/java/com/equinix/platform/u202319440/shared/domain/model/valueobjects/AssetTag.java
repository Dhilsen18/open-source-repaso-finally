package com.equinix.platform.u202319440.shared.domain.model.valueobjects;

import java.util.regex.Pattern;

/**
 * Value object representing an Equinix asset tag.
 *
 * @param value asset tag value
 */
public record AssetTag(String value) {
    private static final Pattern EQUINIX_ASSET_TAG_PATTERN =
            Pattern.compile("^[A-Z]+-[A-Z]+-[A-Z]+-\\d{4}$");

    public AssetTag {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("asset.tag.error.notBlank");
        }
        if (!EQUINIX_ASSET_TAG_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("asset.tag.error.pattern");
        }
    }
}
