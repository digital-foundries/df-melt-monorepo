package com.digital_foundries.mock_image_metadata_service.ImageMetadata;

import com.digital_foundries.mock_image_metadata_service.repository.ImageMetadataRepository;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class ImageMetadataTransformer {


    // Convert Entity to DTO
    public ImageMetadataDto toDTO(ImageMetadataEntity entity) {
        return new ImageMetadataDto(
                entity.getImageId(),
                entity.getOwnerId(),
                entity.getCreatedAt(),
                entity.getSize(),
                entity.getFormat(),
                entity.getExifData(),
                entity.getResolution(),
                entity.getColorMode(),
                entity.getBitDepth()
        );
    }

    // Convert DTO to Entity
    public ImageMetadataEntity toEntity(ImageMetadataDto dto) {
        ImageMetadataEntity entity = new ImageMetadataEntity();
        entity.setImageId(dto.getImageId());
        return entity;
    }
}
