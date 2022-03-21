import config.SparkConfig;
import org.apache.spark.sql.*;
import org.apache.spark.sql.streaming.OutputMode;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;
import service.PipelineService;

import java.io.IOException;

class PipelineApplication{

    public static void main(String[] args) throws StreamingQueryException {


        SparkSession spark=new SparkConfig().initiateSparkSession();
        Dataset<Row> streamDF = spark
                .readStream()
                .format("kafka")
                .option("kafka.bootstrap.servers", "0.0.0.0:9092,0.0.0.0:9093,0.0.0.0:9094")
//                .option("startingOffsets", "earliest")
                .option("subscribe", "iot-data")

                .load().selectExpr("CAST(value AS STRING)");


        StreamingQuery streamingQuery = streamDF
                .writeStream()
                .outputMode(OutputMode.Update())
                .foreach(
                        new ForeachWriter<Row>() {
                            @Override
                            public boolean open(long l, long l1) {
                                return true;
                            }

                            @Override
                            public void process(Row row) {
                                try {
                                    new PipelineService().mapToDevice(row);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }


                            @Override
                            public void close(Throwable throwable) {

                            }

                        }
                ).start();

        streamingQuery.awaitTermination();

    }

}