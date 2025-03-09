digital foundries melt monorepo

Hello!

Melt is my study-level image store system.

Its main functionalities are storing and delivering images in a scalable scale.

Currently, we only have the image-metadata-service implementation started, as it's probably the most simple part of the
system.

Roadmap is
1rst stage:

Image Metadata Service
Service Discovery
Generic inter-service Loadbalancer
Image Store Service
Image Retrieval Service
User Service
Implementing auth
Metrics being outputted to Prometheus

By here we should have a system that runs with a simple docker-compose, and is able to fulfill all the functional
requirements of the system.
-----------------------------------------------

2nd stage:

Centralized logging
Grafana
API Gateway
Setting up the system (non-scalably) on k8s


At this point we should have a system that outputs metrics, has centralized logging, and runs in a k8s instance
-------------------------------------------------------

3rd stage:

Services should be scalable
Loadtesting
Hotspot detection
