package com.digital_foundries.mock_image_metadata_service;

import static org.assertj.core.api.Assertions.assertThat;

import com.digital_foundries.mock_image_metadata_service.ImageMetadata.ImageMetadataDto;
import com.digital_foundries.mock_image_metadata_service.ImageMetadata.ImageMetadataEntity;
import com.digital_foundries.mock_image_metadata_service.ImageMetadata.ImageMetadataTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;

class ImageMetadataTransformerTest {

    private ImageMetadataTransformer transformer;

    @BeforeEach
    void setUp() {
        transformer = new ImageMetadataTransformer();
    }

    @Test
    void testToDTO() {
        ImageMetadataEntity entity = new ImageMetadataEntity(
                1L, 100L, 1234L, Instant.now(), "500mb", "JPEG",
                "EXIF_DATA", "1920x1080", "RGB", "24"
        );

        ImageMetadataDto dto = transformer.toDTO(entity);

        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(entity.getId());
        assertThat(dto.getOwnerId()).isEqualTo(entity.getOwnerId());
        assertThat(dto.getImageId()).isEqualTo(entity.getImageId());
        assertThat(dto.getCreatedAt()).isEqualTo(entity.getCreatedAt());
        assertThat(dto.getSize()).isEqualTo(entity.getSize());
        assertThat(dto.getFormat()).isEqualTo(entity.getFormat());
        assertThat(dto.getExifData()).isEqualTo(entity.getExifData());
        assertThat(dto.getResolution()).isEqualTo(entity.getResolution());
        assertThat(dto.getColorMode()).isEqualTo(entity.getColorMode());
        assertThat(dto.getBitDepth()).isEqualTo(entity.getBitDepth());
    }

    @Test
    void testToEntity() {
        ImageMetadataDto dto = new ImageMetadataDto(
                1L, 100L, 1234L, Instant.now(), "500mb", "JPEG",
                "EXIF_DATA", "1920x1080", "RGB", "24"
        );

        ImageMetadataEntity entity = transformer.toEntity(dto);

        assertThat(entity).isNotNull();
        assertThat(entity.getId()).isEqualTo(dto.getId());
        assertThat(entity.getOwnerId()).isEqualTo(dto.getOwnerId());
        assertThat(entity.getImageId()).isEqualTo(dto.getImageId());
        assertThat(entity.getCreatedAt()).isEqualTo(dto.getCreatedAt());
        assertThat(entity.getSize()).isEqualTo(dto.getSize());
        assertThat(entity.getFormat()).isEqualTo(dto.getFormat());
        assertThat(entity.getExifData()).isEqualTo(dto.getExifData());
        assertThat(entity.getResolution()).isEqualTo(dto.getResolution());
        assertThat(entity.getColorMode()).isEqualTo(dto.getColorMode());
        assertThat(entity.getBitDepth()).isEqualTo(dto.getBitDepth());
    }
}
