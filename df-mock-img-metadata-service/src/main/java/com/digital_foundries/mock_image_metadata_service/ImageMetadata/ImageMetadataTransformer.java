package com.digital_foundries.mock_image_metadata_service.ImageMetadata;

import org.springframework.stereotype.Component;

@Component
public class ImageMetadataTransformer {


    // Convert Entity to DTO
    public ImageMetadataDto toDTO(ImageMetadataEntity entity) {
        return new ImageMetadataDto(
                entity.getId(),
                entity.getOwnerId(),
                entity.getImageId(),
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
        return new ImageMetadataEntity(
                dto.getId(),
                dto.getOwnerId(),
                dto.getImageId(),
                dto.getCreatedAt(),
                dto.getSize(),
                dto.getFormat(),
                dto.getExifData(),
                dto.getResolution(),
                dto.getColorMode(),
                dto.getBitDepth()
        );
    }
}
