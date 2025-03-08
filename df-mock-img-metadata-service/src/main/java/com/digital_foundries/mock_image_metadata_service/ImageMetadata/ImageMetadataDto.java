package com.digital_foundries.mock_image_metadata_service.ImageMetadata;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageMetadataDto {

    @PrimaryKey
    private Long id;
    private Long ownerId;
    private Long imageId;
    private Instant createdAt;
    private String size;
    private String format;
    private String exifData;
    private String resolution;
    private String colorMode;
    private String bitDepth;
}
