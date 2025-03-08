package com.digital_foundries.mock_image_metadata_service.repository;

import com.digital_foundries.mock_image_metadata_service.ImageMetadata.ImageMetadataEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageMetadataRepository extends CassandraRepository<ImageMetadataEntity, Long> {
    // Additional query methods can be defined here if needed
}
