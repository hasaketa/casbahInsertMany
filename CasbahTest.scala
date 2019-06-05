import com.mongodb.casbah.Imports._

object CasbahTest { 
   def main(args: Array[String]) { 
      val mongoClient = MongoClient("localhost", 27017)

      val db = mongoClient("casbahtest")
      db.collectionNames

      val collection = db("unorderedBulkOperation")

      collection.drop()

      val builder = collection.initializeUnorderedBulkOperation

      val startTime = System.currentTimeMillis()

      for ( i <- 0 to 1000000) { 
        builder.insert(MongoDBObject("_id" -> i))
      }

      val result = builder.execute()
      val endTime = System.currentTimeMillis()

      System.out.println("Total execution time: " + (endTime - startTime))
  }

}
