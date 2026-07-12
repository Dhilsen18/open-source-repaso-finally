package com.equinix.platform.u202319440.monitoring.interfaces.rest;

import com.equinix.platform.u202319440.monitoring.application.commandservices.AssetRecordCommandService;
import com.equinix.platform.u202319440.monitoring.interfaces.rest.resources.AssetRecordResource;
import com.equinix.platform.u202319440.monitoring.interfaces.rest.resources.CreateAssetRecordResource;
import com.equinix.platform.u202319440.monitoring.interfaces.rest.transform.CreateAssetRecordCommandFromResourceAssembler;
import com.equinix.platform.u202319440.monitoring.interfaces.rest.transform.ResponseEntityFromAssetRecordCommandResultAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * REST controller for asset record command operations in the monitoring bounded context.
 *
 * @author Dhilsen Armil Mallqui Vilca
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/v1/asset-records", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Asset Records", description = "Asset record operations from the monitoring bounded context")
public class AssetRecordsController {
    private final AssetRecordCommandService assetRecordCommandService;
    private final MessageSource messageSource;

    public AssetRecordsController(
            AssetRecordCommandService assetRecordCommandService,
            MessageSource messageSource) {
        this.assetRecordCommandService = assetRecordCommandService;
        this.messageSource = messageSource;
    }

    @Operation(
            summary = "Create an asset record",
            description = "Registers a new asset record for an existing asset",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Asset record creation request",
                    content = @Content(schema = @Schema(implementation = CreateAssetRecordResource.class))))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Asset record created",
                    content = @Content(schema = @Schema(implementation = AssetRecordResource.class))),
            @ApiResponse(responseCode = "400", description = "Validation error",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404", description = "Asset not found",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PostMapping
    public ResponseEntity<?> createAssetRecord(@Valid @RequestBody CreateAssetRecordResource resource) {
        log.debug("POST /api/v1/asset-records – assetTag={}", resource.assetTag());
        var result = assetRecordCommandService.handle(
                CreateAssetRecordCommandFromResourceAssembler.toCommandFromResource(resource));
        var response = ResponseEntityFromAssetRecordCommandResultAssembler.toResponseEntityFromResult(result, messageSource);
        log.debug("POST /api/v1/asset-records – response status={}", response.getStatusCode());
        return response;
    }
}
