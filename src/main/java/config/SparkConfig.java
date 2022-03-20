package config;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;

public class SparkConfig {


    public SparkSession initiateSparkSession() {

        SparkConf sparkConf = new SparkConf();
        sparkConf.setMaster("local[*]");
        sparkConf.setAppName("Relay42 IOT Pipeline");;
        sparkConf.set("spark.cassandra.connection.host", "0.0.0.0");
        sparkConf.set("spark.cassandra.output.consistency.level", "ONE");
        SparkSession spark = SparkSession.builder().config(sparkConf).getOrCreate();

        return spark;
    }
}
