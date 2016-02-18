import scala.io.Source
import scala.collection.mutable.Queue
import scala.collection.mutable.HashMap

val filename = "moby.txt"

var lines = Queue[String]()
var counts = Queue[HashMap[String, Int]]()

for(line <- Source.fromFile(filename).getLines()) {
  lines.enqueue(line)
}

while(!lines.isEmpty) {
  var line = lines.dequeue()
  var x = new HashMap[String, Int]() { override def default(key: String) = 0}

  for (word <- line.split(" ")) {
    val stripped_word = word.replaceAll("[^\\p{L}\\p{Nd}]+", "").toLowerCase
    x(stripped_word) += 1
  }
  counts.enqueue(x)
}

var totalCounts = new HashMap[String, Int]() { override def default(key: String) = 0}

while(!counts.isEmpty) {
  val map = counts.dequeue()
  map.foreach{ key => 
    totalCounts(key._1) += map(key._1)
  }
}

println(totalCounts.toSeq.sortBy(_._2).takeRight(100))