package com.spark.join

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SQLContext

/**
 * @author jzhang
 */
object HighRatingGenres {
 
  case class ccMovies (
    movieId:String,
    title:String,
    genres:String
  )
  case class ccRatings(
     userId:String,
     movieId:String,
     rating:String,
     timestamp:String
  )
   def main(args:Array[String]) {
     val conf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("MovieJoinService")
      .set("spark.executor.memory", "3g")      
    
     val sc = new SparkContext(conf)
     val sqlContext =new SQLContext(sc)    
   
     import sqlContext.implicits._
     
     val path = ""
      
     val movieText = sc.textFile(path+"movies.csv")
     val header = movieText.first();
     val movieData = movieText.filter(a=>a != header)
     val mData = movieData.map(_.split(",")).map(p => ccMovies(p(0),p(1),p(2)))
     
     val movieDF = mData.toDF();
     movieDF.registerTempTable("MovieTable")
                 
     movieDF.take(10).foreach { println }
     
     val ratingText = sc.textFile(path+"ratings.csv")
     
     val header2 = ratingText.first()
     
     val ratingData = ratingText.filter(a=>a!=header)
     
     val rData = ratingData.map(line=>line.split(",")).map(p => ccRatings(p(0),p(1), p(2),p(3)))
     
     val ratingDF = rData.toDF()
     ratingDF.registerTempTable("RatingTable")
     
     ratingDF.take(100).foreach { println }
     
     // List movies genres which have high rating>3.5
     val results = sqlContext.sql("select a.genres, AVG(b.rating) as average from MovieTable a,  RatingTable b    where  a.movieId = b.movieId group by a.genres having average>3.5 order by average desc")
 
     results.take(500).foreach { println }
  }
}