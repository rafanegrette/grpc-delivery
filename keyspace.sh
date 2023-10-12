#!/usr/bin/env bash

echo "Creating keyspace..."
cqlsh cassandra -u cassandra -p cassandra -e "CREATE KEYSPACE IF NOT EXISTS order_keyspace WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'};"
echo "Finished creating keyspace..."