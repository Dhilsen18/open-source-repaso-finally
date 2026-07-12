package com.equinix.platform.u202319440.elite.interfaces.rest;

import com.equinix.platform.u202319440.elite.application.queryservices.AssetQueryService;
import com.equinix.platform.u202319440.elite.interfaces.rest.resources.AssetResource;
import com.equinix.platform.u202319440.elite.interfaces.rest.transform.AssetResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * REST controller for asset query operations in the elite bounded context.
 *
 * @author Dhilsen Armil Mallqui Vilca
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/v1/assets", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Assets", description = "Asset information from the elite bounded context")
public class AssetsController {
    private final AssetQueryService assetQueryService;

    public AssetsController(AssetQueryService assetQueryService) {
        this.assetQueryService = assetQueryService;
    }

    @Operation(summary = "Get all assets", description = "Returns every asset currently stored in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assets retrieved successfully",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = AssetResource.class))))
    })
    @GetMapping
    public ResponseEntity<?> getAllAssets() {
        log.debug("GET /api/v1/assets");
        var assets = assetQueryService.handle();
        return ResponseEntity.ok(AssetResourceFromEntityAssembler.toResourceListFromEntityList(assets));
    }
}
