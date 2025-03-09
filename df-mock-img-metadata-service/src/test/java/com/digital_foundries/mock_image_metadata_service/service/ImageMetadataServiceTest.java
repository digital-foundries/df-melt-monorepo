package com.digital_foundries.mock_image_metadata_service.service;


import com.digital_foundries.mock_image_metadata_service.ImageMetadata.ImageMetadataDto;
import com.digital_foundries.mock_image_metadata_service.ImageMetadata.ImageMetadataTransformer;
import com.digital_foundries.mock_image_metadata_service.repository.ImageMetadataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


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

    @Test
    public void imageMetadataService_createImageMetadataService() {
        ImageMetadataDto entryDto = new ImageMetadataDto();
        entryDto.setImageId(1l);
        entryDto.setOwnerId(1l);
        entryDto.setCreatedAt(Instant.now());
        entryDto.setFormat("jpeg");
        entryDto.setSize("512mb");
        entryDto.setResolution("1024x1024");
        entryDto.setExifData("asdf");
        entryDto.setColorMode("rbg");
        entryDto.setBitDepth("aaa");

        assertDoesNotThrow(() -> imageMetadataService.createImageMetadata(entryDto));
    }
}
