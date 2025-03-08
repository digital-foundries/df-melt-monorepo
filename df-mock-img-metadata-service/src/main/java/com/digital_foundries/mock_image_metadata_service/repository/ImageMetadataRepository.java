package com.digital_foundries.mock_image_metadata_service.repository;

import com.digital_foundries.mock_image_metadata_service.ImageMetadata.ImageMetadataEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageMetadataRepository extends CassandraRepository<ImageMetadataEntity, Long> {
    // Additional query methods can be defined here if needed

    Page<ImageMetadataEntity> findByUserId(Long userId, Pageable pageable);
}
