package com.digital_foundries.mock_image_metadata_service.service;


import com.digital_foundries.mock_image_metadata_service.ImageMetadata.ImageMetadataDto;
import com.digital_foundries.mock_image_metadata_service.ImageMetadata.ImageMetadataEntity;
import com.digital_foundries.mock_image_metadata_service.ImageMetadata.ImageMetadataTransformer;
import com.digital_foundries.mock_image_metadata_service.repository.ImageMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageMetadataService {



    @Autowired
    ImageMetadataRepository imageMetadataRepository;

    @Autowired
    ImageMetadataTransformer   imageMetadataTransformer;


    public ImageMetadataEntity createImageMetadata(ImageMetadataDto imageMetadataDto){
        return imageMetadataRepository.save(imageMetadataTransformer.toEntity(imageMetadataDto));

    }
}
