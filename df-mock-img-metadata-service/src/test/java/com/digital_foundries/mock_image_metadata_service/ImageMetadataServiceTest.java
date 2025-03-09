package com.digital_foundries.mock_image_metadata_service;


import com.digital_foundries.mock_image_metadata_service.ImageMetadata.ImageMetadataDto;
import com.digital_foundries.mock_image_metadata_service.ImageMetadata.ImageMetadataTransformer;
import com.digital_foundries.mock_image_metadata_service.repository.ImageMetadataRepository;
import com.digital_foundries.mock_image_metadata_service.service.ImageMetadataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class ImageMetadataServiceTest {


    @Mock
    ImageMetadataRepository imageMetadataRepository;

    @Mock
    ImageMetadataTransformer imageMetadataTransformer;

    ImageMetadataService imageMetadataService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.imageMetadataService = new ImageMetadataService(imageMetadataRepository, imageMetadataTransformer);

    }

    @Test public void imageMetadataService_createImageMetadataService() {
        ImageMetadataDto entryDto = new ImageMetadataDto();
        entryDto.setImageId(1l);
        entryDto.setOwnerId(1l);
        entryDto.getCreatedAt();
    }
}
