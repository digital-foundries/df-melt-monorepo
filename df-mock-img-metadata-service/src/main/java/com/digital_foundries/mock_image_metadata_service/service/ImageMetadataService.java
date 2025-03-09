package com.digital_foundries.mock_image_metadata_service.service;


import com.digital_foundries.mock_image_metadata_service.ImageMetadata.ImageMetadataDto;
import com.digital_foundries.mock_image_metadata_service.ImageMetadata.ImageMetadataEntity;
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
        if (imageMetadataRepository.existsById(imageMetadataDto.getImageId())) {
            throw new DuplicateKeyException("Image metadata already exists with this id" + imageMetadataDto.getImageId());
        }
        return imageMetadataTransformer.toDTO(imageMetadataRepository.save(imageMetadataTransformer.toEntity(imageMetadataDto)));
    }


    public ImageMetadataDto find(Long id) throws SQLException {
        return imageMetadataRepository.findById(id)
                .map(imageMetadataTransformer::toDTO) // ✅ Uses Optional.map() to transform directly
                .orElseThrow(() -> new EntityNotFoundException("Metadata not found for ID: " + id)); // ✅ Returns null if not found (or you can throw an exception)
    }


    public Slice<ImageMetadataDto> getByOwnerId(Long userId, Pageable pageable) throws SQLException {
        Slice<ImageMetadataEntity> entityPage = imageMetadataRepository.findByKeyOwnerId(userId, pageable);
        return entityPage.map(imageMetadataTransformer::toDTO);
    }

}
