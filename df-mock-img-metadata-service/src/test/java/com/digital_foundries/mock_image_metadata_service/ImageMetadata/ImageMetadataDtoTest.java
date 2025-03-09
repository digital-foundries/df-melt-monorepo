package com.digital_foundries.mock_image_metadata_service.ImageMetadata;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ImageMetadataDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidImageMetadataDto() {
        ImageMetadataDto dto = new ImageMetadataDto(
                1L, 2L, Instant.now(), "1024x768", "JPEG", "Some EXIF Data", "1920x1080", "RGB", "8"
        );
        Set<ConstraintViolation<ImageMetadataDto>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
        dto = new ImageMetadataDto(
                1L, 2L, Instant.now(), "1024x768", "JPEG", null, "1920x1080", "RGB", "8"
        );
        violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidImageMetadataDto() {
        ImageMetadataDto dto = new ImageMetadataDto(
                null, null, null, null, null, "Some EXIF Data", null, null, null
        );
        Set<ConstraintViolation<ImageMetadataDto>> violations = validator.validate(dto);
        assertEquals(8, violations.size());
    }
}

