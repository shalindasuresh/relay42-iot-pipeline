package com.relay42.iot.pipeline.service;

import com.relay42.iot.pipeline.config.SparkConfig;
import com.relay42.iot.pipeline.mapper.DeviceRead;
import org.apache.commons.net.util.Base64;
import org.apache.spark.sql.*;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;

public class PipelineService {


    public  void mapToDevice(Row row) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        byte[] valueDecoded = Base64.decodeBase64(String.valueOf(row));


        DeviceRead deviceRead = objectMapper.readValue(new String(valueDecoded), DeviceRead.class);

        Encoder<DeviceRead> encoder = Encoders.bean(DeviceRead.class);

        SparkSession spark= new SparkConfig().initiateSparkSession();

        Dataset<DeviceRead> dataset = spark.createDataset(Arrays.asList(deviceRead), encoder);


        dataset.write()
                .mode("append")
                .format("org.apache.spark.sql.cassandra").option("keyspace", "relay42")
                .option("table", "device_read")
                .save();
    }
}
