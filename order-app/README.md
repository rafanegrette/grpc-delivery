# E-Delivery
## Order App

Configuration steps for local environment:

1) Install Java 17
2) Install Rancher Desktop
3) Configure the database, execute by command line:
   1) docker pull cassandra
   2) docker run --name order-db -p 9042:9042 -d cassandra:latest
   3) docker exec -it order-db bash -c "cqlsh -u cassandra -p cassandra"
   4) CREATE KEYSPACE order_keyspace WITH replication = { 'class': 'SimpleStrategy', 'replication_factor':1}
   5) exit
4) execute in the root folder: ./gradlew bootRun 