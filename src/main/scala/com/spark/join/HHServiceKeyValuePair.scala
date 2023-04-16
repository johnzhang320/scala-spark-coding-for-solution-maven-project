package com.spark.join

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

/**
 *   
 */
//https://sparkbyexamples.com/spark/how-to-create-an-rdd-using-parallelize/
object  HHServiceKeyValuePair  {

// Two RDDs join 
  def main(args: Array[String]) {

   /*
     SQLContext is deprecated and it will be replace SparkSession which support DataFrame and SQL operation , use Hive list and distribute search
     val conf = new SparkConf().setAppName("HHService").setMaster("local[*]")
     val sc = new SparkContext(conf)
     val sqlContext = new org.apache.spark.sql.SQLContext(sc)
     import sqlContext.implicits._

    */
     val spark = SparkSession.builder
       .master("local[*]")
       .appName("HHService")
       .getOrCreate()
     val sc = spark.sparkContext;
     val hservice = sc.textFile("services.csv")
     val header = hservice.first()
     val hsRDD = hservice.filter(a => a != header)
     val hss = hsRDD.map(x => (x.split(",")(0), x))

     val hregistration = sc.textFile("registration.csv")
     val header2 = hregistration.first()
     val hrRDD = hregistration.filter(a => a!= header2)
     val hrSS = hrRDD.map(x => (x.split(",")(0),x.split(",")(3)))

     val r = hss.join(hrSS)
     r.take(500).foreach(println)
    // r.saveAsTextFile("results")

   }
}
