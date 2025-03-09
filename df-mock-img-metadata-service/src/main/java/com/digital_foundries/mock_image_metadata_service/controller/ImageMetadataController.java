package com.digital_foundries.mock_image_metadata_service.controller;


import com.digital_foundries.mock_image_metadata_service.ImageMetadata.ImageMetadataDto;
import com.digital_foundries.mock_image_metadata_service.service.ImageMetadataService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.constraints.NotNull;

import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/image-metadata")
@Validated
class ImageMetadataController {

    Logger logger = LoggerFactory.getLogger(ImageMetadataController.class);


    private final ImageMetadataService imageMetadataService;

    public ImageMetadataController(
            ImageMetadataService imageMetadataService
    ) {
        this.imageMetadataService = imageMetadataService;
    }

    @PostMapping
    public ResponseEntity<?> postDto(@RequestBody @Valid ImageMetadataDto imageMetadataDto, BindingResult bindingResult) {

        logger.info("POST /api/v1/image-metadata/postDto imageId:" + imageMetadataDto.getImageId());
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(error ->
                    logger.warn("Validation failed for field '{}': {}", error.getField(), error.getDefaultMessage())
            );
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        try {
            ImageMetadataDto outputDto = imageMetadataService.createImageMetadata(imageMetadataDto);
            logger.info("POST /api/v1/image-metadata/postDto SUCCESS imageId:" + outputDto.getImageId());
            return ResponseEntity.status(HttpStatus.CREATED).body(outputDto);
        } catch (DuplicateKeyException e) {
            logger.error("DuplicateKeyException", e);
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", "Database error", "details", e.getMessage()));
        } catch (SQLException e) {
            logger.error("SqlException", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Database error", "details", e.getMessage()));
        } catch (Exception e) {
            logger.error("Exception", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Unexpected error", "details", e.getMessage()));
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDto(@PathVariable Long id) {
        try {
            ImageMetadataDto dto = imageMetadataService.find(id);
            if (dto == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(dto); // HTTP 200 OK
        } catch (SQLException e) {
            logger.error("SqlException", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Database error", "details", e.getMessage()));
        } catch (Exception e) {
            logger.error("Exception", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Unexpected error", "details", e.getMessage()));
        }
    }


    private final Set<String> ALLOWED_SORT_FIELDS = Set.of("imageId", "createdAt", "size");

    @GetMapping("/getByOwnerId/{userId}")
    public ResponseEntity<?> getByOwnerId(@PathVariable @NotNull Long userId, @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "imageId") String sortBy,
                                          @RequestParam(defaultValue = "asc") String direction) {

        if (!ALLOWED_SORT_FIELDS.contains(sortBy)) {
            logger.error("Invalid sort field: " + sortBy);
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Invalid sortBy field",
                            "allowedFields", ALLOWED_SORT_FIELDS));
        }

        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        try {
            Slice<ImageMetadataDto> entityPage = imageMetadataService.getByOwnerId(userId, pageable);
            return ResponseEntity.ok(entityPage.getContent());
        } catch (SQLException e) {
            logger.error("SqlException", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Database error", "details", e.getMessage()));
        } catch (Exception e) {
            logger.error("Exception", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Unexpected error", "details", e.getMessage()));
        }

    }

}