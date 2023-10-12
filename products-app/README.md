# gRPC-Delivery
## Products-app

Configuration steps for local environment:

1) Install Java 17.
2) Install Docker app locally (Rancher Desktop, Docker desktop, etc).
3) In the products-app root folder execute the command: "docker compose up".
4) Execute "./gradlew clean"
5) Execute "./gradlew build"
6) Start GrpcDeliveryApplication

For testing use PostMan importing the proto file "products-proto"