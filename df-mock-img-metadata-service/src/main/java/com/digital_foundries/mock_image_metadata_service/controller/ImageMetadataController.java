package com.digital_foundries.mock_image_metadata_service.controller;


import com.digital_foundries.mock_image_metadata_service.ImageMetadata.ImageMetadataDto;
import com.digital_foundries.mock_image_metadata_service.service.ImageMetadataService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    private final ImageMetadataService imageMetadataService;

    public ImageMetadataController(
            ImageMetadataService imageMetadataService
    ) {
        this.imageMetadataService = imageMetadataService;
    }

    @PostMapping
    public ResponseEntity<?> postDto(@RequestBody ImageMetadataDto imageMetadataDto) {
        try {
            ImageMetadataDto outputDto = imageMetadataService.createImageMetadata(imageMetadataDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(outputDto);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Database error", "details", e.getMessage()));
        } catch (Exception e) {
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Database error", "details", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Unexpected error", "details", e.getMessage()));
        }
    }


    private final Set<String> ALLOWED_SORT_FIELDS = Set.of("id", "createdAt", "imageId", "size");

    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<?> getByUserId(@PathVariable @NotNull Long userId, @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                         @RequestParam(defaultValue = "id") String sortBy,
                                         @RequestParam(defaultValue = "asc") String direction) {

        if (!ALLOWED_SORT_FIELDS.contains(sortBy)) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Invalid sortBy field",
                            "allowedFields", ALLOWED_SORT_FIELDS));
        }

        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        try {
            Page<ImageMetadataDto> entityPage = imageMetadataService.getByUserId(userId, pageable);
            return ResponseEntity.ok(entityPage);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Database error", "details", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Unexpected error", "details", e.getMessage()));
        }

    }

}