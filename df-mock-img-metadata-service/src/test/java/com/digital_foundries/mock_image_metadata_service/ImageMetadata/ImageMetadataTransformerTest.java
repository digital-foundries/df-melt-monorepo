package com.digital_foundries.mock_image_metadata_service.ImageMetadata;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

class ImageMetadataTransformerTest {

    private ImageMetadataTransformer transformer;


    @BeforeEach
    void setUp() {
        transformer = new ImageMetadataTransformer();
    }

    @Test
    void testToDTO() {

        ImageMetadataPrimaryKey key = new ImageMetadataPrimaryKey(1L, 100L);
        ImageMetadataEntity entity = new ImageMetadataEntity(
                key, Instant.now(), "500mb", "JPEG",
                "EXIF_DATA", "1920x1080", "RGB", "24"
        );

        ImageMetadataDto dto = transformer.toDTO(entity);

        assertThat(dto).isNotNull();
        assertThat(dto.getOwnerId()).isEqualTo(entity.getKey().getOwnerId());
        assertThat(dto.getImageId()).isEqualTo(entity.getKey().getImageId());
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
                1L, 100L, Instant.now(), "500mb", "JPEG",
                "EXIF_DATA", "1920x1080", "RGB", "24"
        );

        ImageMetadataEntity entity = transformer.toEntity(dto);

        assertThat(entity).isNotNull();
        assertThat(entity.getKey().getImageId()).isNotNull();
        assertThat(entity.getKey().getOwnerId()).isNotNull();
        assertThat(entity.getBitDepth()).isNotNull();
        assertThat(entity.getCreatedAt()).isNotNull();
        assertThat(entity.getSize()).isNotNull();
        assertThat(entity.getFormat()).isNotNull();
        assertThat(entity.getExifData()).isNotNull();
        assertThat(entity.getResolution()).isNotNull();
        assertThat(entity.getColorMode()).isNotNull();
        assertThat(entity.getExifData()).isNotNull();
        
    }
}
