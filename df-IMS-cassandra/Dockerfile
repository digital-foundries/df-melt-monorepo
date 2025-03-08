# Use the official Cassandra 5.0 image from Docker Hub
FROM cassandra:5.0

# Set environment variables for basic Cassandra configuration
ENV CASSANDRA_CLUSTER_NAME=LocalCluster
ENV CASSANDRA_LISTEN_ADDRESS=0.0.0.0
ENV CASSANDRA_BROADCAST_ADDRESS=127.0.0.1
ENV CASSANDRA_RPC_ADDRESS=0.0.0.0
ENV CASSANDRA_SEEDS=127.0.0.1

# Expose required ports for Cassandra communication
EXPOSE 7000 7001 7199 9042 9160

# Create directories to persist data
VOLUME ["/var/lib/cassandra", "/opt/cassandra/logs"]

# Use a custom cassandra.yaml file (optional, replace path if you have one)
#COPY cassandra.yaml /etc/cassandra/cassandra.yaml

# Start Cassandra on container startup
CMD ["cassandra", "-f"]