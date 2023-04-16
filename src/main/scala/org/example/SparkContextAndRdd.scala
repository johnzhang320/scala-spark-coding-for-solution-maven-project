package org.example
import org.apache.spark.sql.SparkSession
/**
 * SparkContext is an entry point to Spark and is defined in org.apache.spark package.
 * It is used to programmatically create Spark RDD, accumulators, and broadcast variables
 * on the cluster
 * uses SparkContext to connect to the cluster manager to submit Spark jobs, and know what
 * resource manager (YARN, Mesos or Standalone) to communicate to. It is the heart of the
 * Spark application.
 */
object SparkContextAndRdd extends App{

    val spark = SparkSession.builder()
      .master("local[1]")
      .appName("SparkByExamples.com")
      .getOrCreate();
    println(spark.sparkContext)
    println("Spark App Name : "+spark.sparkContext.appName)

    // Create RDD
    val rdd = spark.sparkContext.range(1, 15)
    rdd.collect().foreach(println)

    // Create RDD from Text file
    val rdd2 = spark.sparkContext.textFile("~/ScalaSparkKafka/runnable/scala-spark-maven-example/test_data/John_Zhang_Resume.txt")

}
