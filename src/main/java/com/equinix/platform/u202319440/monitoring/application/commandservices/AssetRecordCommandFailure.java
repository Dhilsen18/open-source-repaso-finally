package com.equinix.platform.u202319440.monitoring.application.commandservices;

/**
 * Sealed hierarchy describing asset record command failures.
 */
public sealed interface AssetRecordCommandFailure permits AssetRecordCommandFailure.AssetNotFound {

    String messageKey();

    record AssetNotFound() implements AssetRecordCommandFailure {
        @Override
        public String messageKey() {
            return "asset.error.notFound";
        }
    }
}
