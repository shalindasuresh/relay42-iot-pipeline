
# Relay42 IOT Device Pipeline

A brief description of what this project does and who it's for


![Logo](https://www.pngkey.com/png/detail/189-1890854_coo-relay42-transparent-logo.png)


# Relay42 IOT Device Pipeline

The solution is java based big data processing ETL pipeline especially designed for support scalable and distributed computing capable of handline stream of data.


## Pipeline Data flow

![Pipeline](https://shalindarelay42.s3.amazonaws.com/Untitled+Diagram.drawio.png)
## Software Stack

### Apache Spark
Benefits to the solution :

    * Supports distributed computation across cluster of nodes
    * Digest Kafka data from multiple brokers as streaming
    * Transform data using Dataset or SQL queries

### Apache Cassandra
Benefits to the solution :

    * Runs on low end hardware
    * Write operations are faster which is ideal for streaming data storing
    * Data can be partition to retrieve efficiently and faster
    * No master or leader node meaning great support for fault tolerance

### Docker

    * Less overhead on running application on any operating systems
    * Performant than VM
    * Required packages can be bundled

## UseCase Diagram


![UseCaseDiagram](https://shalindarelay42.s3.amazonaws.com/Data+Pipeline+use+case.drawio.png)



## Run Locally

### Note : Please run data producer simulator at https://github.com/operations-relay42/iot-producer-simulator-api


Start Cassandra

```bash
  docker-compose up -d
```


SSH into Cassandra

```bash
  docker exec -u root  -t -i  cassandra_db  /bin/bash
```

CQLSH command

```bash
  cqlsh
```

Create Keyspace in cassandra
```bash
CREATE KEYSPACE IF NOT EXISTS "relay42" WITH REPLICATION =
{ 'class': 'SimpleStrategy',
'replication_factor': '3'
};
```

Create table in cassandra
```bash
Create table relay42.device_read
(
"id" int,
"type"  text,
"name" text,
"clusterId" int,
"timestamp" text ,
"value" double,
"initialized" boolean,
Primary key((type),timestamp)
);

```

Create column index
```bash
CREATE INDEX ON relay42.device_read ("name");

```

Exit container

```bash
  exit
```



Install dependencies

```bash
  mvn clean install
```

Start the pipeline

```bash
   mvn exec:java -Dexec.mainClass="com.relay42.iot.pipeline.PipelineApplication"

```


## Access Spark UI

http://localhost:4040/