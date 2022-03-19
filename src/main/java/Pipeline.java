import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

class Pipeline {

    public static void main(String[] args) {


        SparkSession spark=initiateSparkSession();
        Dataset<Row> df = spark
                .read()
                .format("kafka")
                .option("kafka.bootstrap.servers", "0.0.0.0:9092,0.0.0.0:9093,0.0.0.0:9094")
                .option("startingOffsets", "earliest")
                .option("subscribe", "iot-data")
                .load();
        Dataset<Row> personStringDF = df.selectExpr("CAST(value AS STRING)");
        System.out.println(personStringDF);


    }



    public static SparkSession initiateSparkSession() {

        SparkConf sparkConf = new SparkConf();
        sparkConf.setMaster("local[*]");
        sparkConf.setAppName("Relay42 IOT Pipeline");;
        SparkSession spark = SparkSession.builder().config(sparkConf).getOrCreate();

        return spark;
    }
}