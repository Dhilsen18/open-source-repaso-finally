package com.equinix.platform.u202319440.monitoring.interfaces.rest.transform;

import com.equinix.platform.u202319440.monitoring.application.commandservices.AssetRecordCommandFailure;
import com.equinix.platform.u202319440.monitoring.domain.model.aggregates.AssetRecord;
import com.equinix.platform.u202319440.shared.application.result.Result;
import com.equinix.platform.u202319440.shared.interfaces.rest.RequestLocaleResolver;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Locale;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * Assembler for mapping asset record command results to HTTP responses.
 */
public class ResponseEntityFromAssetRecordCommandResultAssembler {

    private ResponseEntityFromAssetRecordCommandResultAssembler() {
    }

    /**
     * Converts a command result into an HTTP response entity.
     *
     * @param result command execution result
     * @param messageSource message source for localized errors
     * @return HTTP response entity
     */
    public static ResponseEntity<?> toResponseEntityFromResult(
            Result<AssetRecord, AssetRecordCommandFailure> result,
            MessageSource messageSource) {
        return result.fold(
                assetRecord -> new ResponseEntity<>(
                        AssetRecordResourceFromEntityAssembler.toResourceFromEntity(assetRecord),
                        CREATED),
                failure -> {
                    HttpStatus status = statusFromFailure(failure);
                    return ResponseEntity.status(status).body(ProblemDetail.forStatusAndDetail(
                            status,
                            localizedMessage(messageSource, failure.messageKey())));
                });
    }

    private static HttpStatus statusFromFailure(AssetRecordCommandFailure failure) {
        if (failure instanceof AssetRecordCommandFailure.AssetNotFound) {
            return HttpStatus.NOT_FOUND;
        }
        return HttpStatus.BAD_REQUEST;
    }

    private static String localizedMessage(MessageSource messageSource, String messageKey) {
        Locale locale = Locale.ENGLISH;
        var attributes = RequestContextHolder.getRequestAttributes();
        if (attributes instanceof ServletRequestAttributes servletRequestAttributes) {
            locale = RequestLocaleResolver.resolve(servletRequestAttributes.getRequest());
        }
        return messageSource.getMessage(messageKey, null, messageKey, locale);
    }
}
