package com.digital_foundries.mock_image_metadata_service.ImageMetadata;

import com.digital_foundries.mock_image_metadata_service.repository.ImageMetadataRepository;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class ImageMetadataTransformer {


    // Convert Entity to DTO
    public ImageMetadataDto toDTO(ImageMetadataEntity entity) {
        return new ImageMetadataDto(
                entity.getKey().getImageId(),
                entity.getKey().getOwnerId(),
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
        ImageMetadataPrimaryKey key = new ImageMetadataPrimaryKey(dto.getOwnerId(), dto.getImageId());
        entity.setKey(key);
        entity.setCreatedAt(dto.getCreatedAt() == null ? null : dto.getCreatedAt());
        entity.setSize(dto.getSize() == null ? null : dto.getSize());
        entity.setFormat(dto.getFormat() == null ? null : dto.getFormat());
        entity.setExifData(dto.getExifData() == null ? null : dto.getExifData());
        entity.setResolution(dto.getResolution() == null ? null : dto.getResolution());
        entity.setColorMode(dto.getColorMode() == null ? null : dto.getColorMode());
        entity.setBitDepth(dto.getBitDepth() == null ? null : dto.getBitDepth());

        return entity;
    }
}
