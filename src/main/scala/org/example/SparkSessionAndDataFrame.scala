package org.example
import org.apache.spark.sql.SparkSession
object SparkSessionAndDataFrame extends App {
   println("Finally Spark begin running")
  val spark = SparkSession.builder()
    .master("local[1]")
    .appName("SparkByExample")
    .getOrCreate();

  println("First SparkContext:")
  println("APP Name :" + spark.sparkContext.appName);
  println("Deploy Mode :" + spark.sparkContext.deployMode);
  println("Master :" + spark.sparkContext.master);
  println("Spark Version : "+spark.version);

  val sparkSession2 = SparkSession.builder()
    .master("local[1]")
    .appName("SparkByExample-test")
    .getOrCreate();

  println("Second SparkContext:")
  println("APP Name :" + sparkSession2.sparkContext.appName);
  println("Deploy Mode :" + sparkSession2.sparkContext.deployMode);
  println("Master :" + sparkSession2.sparkContext.master);
  println("Spark Version : "+sparkSession2.version);
  println("\n-------------------------------------------------------------\n");
  val spark3 = spark.newSession();
  println("Create new Session from spark, context as following")
  println("APP Name :" + spark3.sparkContext.appName);
  println("Deploy Mode :" + spark3.sparkContext.deployMode);
  println("Master :" + spark3.sparkContext.master);
  println("Spark Version : " + spark3.version);

  println("\n--------------------------Config -------------------------------------\n");

  val spark4 = SparkSession.builder()
    .master("local[1]")
    .appName("SparkByExamples.com")
    .config("spark.some.config.option", "setting bryan up")
    .getOrCreate();
  println("Create a Session with config properties, context as following")
  println("APP Name :" + spark4.sparkContext.appName);
  println("Deploy Mode :" + spark4.sparkContext.deployMode);
  println("Master :" + spark4.sparkContext.master);
  println("Config :" + spark4.sparkContext.getConf);
  println("Spark Version : " + spark4.version);

  println("\n--------------------------DataFrame Demo-------------------------------------\n");
  val df = spark.createDataFrame(
    List(("Scala", 25000), ("Spark", 35000), ("PHP", 21000)))
  df.show()

  println("Finally Spark End running")
}
