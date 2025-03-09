package com.digital_foundries.mock_image_metadata_service.repository;

import com.digital_foundries.mock_image_metadata_service.ImageMetadata.ImageMetadataEntity;
import com.digital_foundries.mock_image_metadata_service.ImageMetadata.ImageMetadataPrimaryKey;
import org.springframework.data.cassandra.core.WriteResult;
import org.springframework.data.cassandra.repository.CassandraRepository;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface ImageMetadataRepository extends CassandraRepository<ImageMetadataEntity, ImageMetadataPrimaryKey> {
    // Additional query methods can be defined here if needed


    @Query("INSERT INTO image_metadata (imageid, ownerid, createdat, size, format, exifdata, resolution, colormode, bitdepth) " +
            "VALUES (?0, ?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8) IF NOT EXISTS")
    WriteResult insertIfNotExists(Long imageId, Long ownerId, Instant createdAt, String size,
                                  String format, String exifData, String resolution,
                                  String colorMode, String bitDepth);

    Slice<ImageMetadataEntity> findByKeyOwnerId(Long userId, Pageable pageable);

}
