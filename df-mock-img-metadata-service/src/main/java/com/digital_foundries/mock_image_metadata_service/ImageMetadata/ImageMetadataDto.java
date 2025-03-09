package com.digital_foundries.mock_image_metadata_service.ImageMetadata;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private Long imageId;
    @NotNull
    private Long ownerId;
    @NotNull
    private Instant createdAt;
    @NotNull
    private String size;
    @NotNull
    private String format;
    @Nullable
    private String exifData;
    @NotNull
    private String resolution;
    @NotNull
    private String colorMode;
    @NotNull
    private String bitDepth;
}
