package com.digital_foundries.mock_image_metadata_service.controller;

import com.digital_foundries.mock_image_metadata_service.ImageMetadata.ImageMetadataDto;
import com.digital_foundries.mock_image_metadata_service.service.ImageMetadataService;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Instant;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

class ImageMetadataControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ImageMetadataService imageMetadataService;

    @InjectMocks
    private ImageMetadataController imageMetadataController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc = MockMvcBuilders.standaloneSetup(imageMetadataController).build();
    }

    @Test
    void testPostDto_ValidInput_ShouldReturn201() throws Exception {
        ImageMetadataDto requestDto = new ImageMetadataDto(1L, 2L, Instant.now(), "1024x768", "JPEG", "Some EXIF", "1920x1080", "RGB", "8");
        when(imageMetadataService.createImageMetadata(any())).thenReturn(requestDto);

        mockMvc.perform(post("/api/v1/image-metadata")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.imageId").value(1))
                .andExpect(jsonPath("$.ownerId").value(2));
    }

    @Test
    void testPostDto_InvalidInput_ShouzldReturn400() throws Exception {
        ImageMetadataDto invalidDto = new ImageMetadataDto(null, null, null, null, null, null, null, null, null);

        mockMvc.perform(post("/api/v1/image-metadata")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetDto_ValidId_ShouldReturn200() throws Exception {
        ImageMetadataDto responseDto = new ImageMetadataDto(1L, 2L, Instant.now(), "1024x768", "JPEG", "Some EXIF", "1920x1080", "RGB", "8");
        when(imageMetadataService.find(2L, 1L)).thenReturn(responseDto);

        mockMvc.perform(get("/api/v1/image-metadata/2/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.imageId").value(1));
    }

    @Test
    void testGetDto_NotFound_ShouldReturn404() throws Exception {
        when(imageMetadataService.find(1L, 1L)).thenReturn(null);

        mockMvc.perform(get("/api/v1/image-metadata/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testgetByOwnerId_ValidUserId_ShouldReturn200() throws Exception {
        ImageMetadataDto dto = new ImageMetadataDto(1L, 2L, Instant.now(), "1024x768", "JPEG", "Some EXIF", "1920x1080", "RGB", "8");
        Page<ImageMetadataDto> page = new PageImpl<>(List.of(dto));
        when(imageMetadataService.getByOwnerId(any(), any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/api/v1/image-metadata/getByOwnerId/2?page=0&size=10&sortBy=imageId&direction=asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].imageId").value(1));
    }

    @Test
    void testgetByOwnerId_InvalidSortField_ShouldReturn400() throws Exception {
        mockMvc.perform(get("/api/v1/image-metadata/getByOwnerId/2?page=0&size=10&sortBy=invalidField&direction=asc"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid sortBy field"));
    }
}

