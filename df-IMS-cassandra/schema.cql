-- Create the keyspace if it does not exist
CREATE KEYSPACE IF NOT EXISTS image_metadata
    WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'};

-- Use the keyspace
USE spring_cassandra;

-- Create the table to store image metadata
CREATE TABLE IF NOT EXISTS image_metadata (
                                              id BIGINT PRIMARY KEY,
                                              created_at TIMESTAMP,
                                              size TEXT,
                                              format TEXT,
                                              exif_data TEXT,
                                              resolution TEXT,
                                              color_mode TEXT,
                                              bit_depth TEXT
);
