// 22:54:42:~/src/scala_by_example/moby (master)$ time scala ThreadedMoby
// 1450100

// real  0m7.789s
// user  0m24.925s
// sys 0m2.767s
// 22:55:27:~/src/scala_by_example/moby (master)$ time scala ThreadedMoby
// 1450100

// real  0m7.982s
// user  0m25.192s
// sys 0m2.741s
// 22:55:36:~/src/scala_by_example/moby (master)$ time scala ThreadedMoby
// 1450100

// real  0m7.882s
// user  0m25.312s
// sys 0m2.736s
// 22:55:45:~/src/scala_by_example/moby (master)$ time scala ThreadedMoby
// 1450100

// real  0m7.797s
// user  0m25.008s
// sys 0m2.761s

import scala.io.Source
import scala.collection.mutable.HashMap
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.TimeUnit

object ThreadedMoby {

  class Broker {

    var totalCounts = new HashMap[String, Int]() { override def default(key: String) = 0}

    var queue: ArrayBlockingQueue[String] = new ArrayBlockingQueue[String](100)

    var continueProducing: java.lang.Boolean = true

    def put(data: String) {
      this.queue.put(data)
    }

    def get(): String = this.queue.poll()

    def combineMaps(map_a: HashMap[String,Int], map_b: HashMap[String,Int]) = {
      this.synchronized {
        map_b.foreach{ key => 
          map_a(key._1) += map_b(key._1)
        }
      }
    }
  }

  class Producer(private var broker: Broker) extends Runnable {
    val filename = "moby.txt"
    override def run() {
      try {
        1 to 100 foreach { _ => 
          for(line <- Source.fromFile(filename).getLines()) {
            broker.put(line)
          }
        }
        this.broker.continueProducing = false
      } catch {
        case ex: InterruptedException => ex.printStackTrace()
      }
    }
  }

  class Consumer(private var name: String, private var broker: Broker) extends Runnable {

    override def run() {
      try {
        var data:String = null
        while (broker.continueProducing || data != null) {
          data = broker.get
          if(data != null) {
            var x = new HashMap[String, Int]() { override def default(key: String) = 0}

            for (word <- data.split(" ")) {
              val stripped_word = word.replaceAll("[^\\p{L}\\p{Nd}]+", "").toLowerCase
              x(stripped_word) += 1
            }
            broker.combineMaps(broker.totalCounts, x)  
          }
        }
      } catch {
        case ex: InterruptedException => ex.printStackTrace()
      }
    }
  }

  def main(args: Array[String]) {
    try {
      val broker = new Broker()
      val numThreads = 4
      val threadPool = Executors.newFixedThreadPool(numThreads)
      
      for (i <- 0 until numThreads - 1 ) { // leave additional space for producer
        threadPool.execute(new Consumer(i.toString, broker))        
      }

      val producerStatus = threadPool.submit(new Producer(broker))
      producerStatus.get

      threadPool.shutdown()
      
      // Wait for the last thread to finish after shutdown
      threadPool.awaitTermination(1, TimeUnit.SECONDS)

      println(broker.totalCounts("the"))
    } catch {
      case e: Exception => e.printStackTrace()
    }
  }
}
