package com.digital_foundries.mock_image_metadata_service.ImageMetadata;

import lombok.Getter;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;

@PrimaryKeyClass
@Getter
public class ImageMetadataPrimaryKey implements Serializable {

    @PrimaryKeyColumn(name = "ownerid", type = PrimaryKeyType.PARTITIONED) // Partition Key
    private Long ownerId;

    @PrimaryKeyColumn(name = "imageid", type = PrimaryKeyType.CLUSTERED) // Clustering Key
    private Long imageId;

    public ImageMetadataPrimaryKey(Long ownerId, Long imageId) {
        this.ownerId = ownerId;
        this.imageId = imageId;
    }
}

