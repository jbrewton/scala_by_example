// 22:53:25:~/src/scala_by_example/moby (master)$ time scala Moby
// 1450100

// real  0m17.040s
// user  0m18.730s
// sys 0m0.314s
// 22:53:49:~/src/scala_by_example/moby (master)$ time scala Moby
// 1450100

// real  0m16.422s
// user  0m18.054s
// sys 0m0.319s
// 22:54:07:~/src/scala_by_example/moby (master)$ time scala Moby
// 1450100

// real  0m16.101s
// user  0m17.843s
// sys 0m0.318s
// 22:54:25:~/src/scala_by_example/moby (master)$ time scala Moby
// 1450100

// real  0m16.432s
// user  0m17.986s
// sys 0m0.315s

import scala.io.Source
import scala.collection.mutable.HashMap

object Moby {

  def main(args: Array[String]) {

    val filename = "moby.txt"
    var totalCounts = new HashMap[String, Int]() { override def default(key: String) = 0}

    1 to 100 foreach { _ => 
      for(line <- Source.fromFile(filename).getLines()) {
        for (word <- line.split(" ")) {
          val stripped_word = word.replaceAll("[^\\p{L}\\p{Nd}]+", "").toLowerCase
          totalCounts(stripped_word) += 1
        }
      }
    }

    println(totalCounts("the"))
  }
}
// println(totalCounts.toSeq.sortBy(_._2).takeRight(100))
