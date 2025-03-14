package com.digital_foundries.mock_image_metadata_service.service;


import com.digital_foundries.mock_image_metadata_service.ImageMetadata.ImageMetadataDto;
import com.digital_foundries.mock_image_metadata_service.ImageMetadata.ImageMetadataEntity;
import com.digital_foundries.mock_image_metadata_service.ImageMetadata.ImageMetadataPrimaryKey;
import com.digital_foundries.mock_image_metadata_service.ImageMetadata.ImageMetadataTransformer;
import com.digital_foundries.mock_image_metadata_service.exception.EntityNotFoundException;
import com.digital_foundries.mock_image_metadata_service.repository.ImageMetadataRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class ImageMetadataService {


    final
    ImageMetadataRepository imageMetadataRepository;

    final
    ImageMetadataTransformer imageMetadataTransformer;

    public ImageMetadataService(ImageMetadataRepository imageMetadataRepository, ImageMetadataTransformer imageMetadataTransformer) {
        this.imageMetadataRepository = imageMetadataRepository;
        this.imageMetadataTransformer = imageMetadataTransformer;
    }


    public ImageMetadataDto createImageMetadata(ImageMetadataDto imageMetadataDto) throws SQLException, DuplicateKeyException {
        if (imageMetadataRepository.existsById(new ImageMetadataPrimaryKey(imageMetadataDto.getImageId(), imageMetadataDto.getOwnerId()))) {
            throw new DuplicateKeyException("Image metadata already exists with this id" + imageMetadataDto.getImageId());
        }
        return imageMetadataTransformer.toDTO(imageMetadataRepository.save(imageMetadataTransformer.toEntity(imageMetadataDto)));
    }


    public ImageMetadataDto find(Long imageId, Long ownerId) throws SQLException {
        return imageMetadataRepository.findById(new ImageMetadataPrimaryKey(imageId, ownerId))
                .map(imageMetadataTransformer::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Metadata not found for PK: " + new ImageMetadataPrimaryKey(imageId, ownerId)));
    }


    public Slice<ImageMetadataDto> getByOwnerId(Long ownerId, Pageable pageable) throws SQLException {
        Slice<ImageMetadataEntity> entityPage = imageMetadataRepository.findByKeyOwnerId(ownerId, pageable);
        return entityPage.map(imageMetadataTransformer::toDTO);
    }

}
