package com.digital_foundries.mock_image_metadata_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@SpringBootApplication
@EnableCassandraRepositories(basePackages = "com.digital_foundries.mock_image_metadata_service.ImageMetadata")
public class MockImageMetadataServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(MockImageMetadataServiceApplication.class, args);
	}
}
