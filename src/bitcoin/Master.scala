package bitcoin

import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.routing.RoundRobinRouter
import scala.collection.mutable.HashMap

class Master(mask: Int, gatorID: String) extends Actor {
  val factor: Int = 2
  var numOfWorkersDone = 0
  val resultMap: Map[String, String] = Map()
  val jobs = 20
  var textLength = 1

  val cores: Int = Runtime.getRuntime().availableProcessors();

  val workerRouter = context.actorOf(
    Props(new Worker(mask)).withRouter(RoundRobinRouter(cores * factor)), name = "workerRouter")

  def receive = {
    case RegisterToSuperMaster =>
      sender() ! registerToSuperMaster()
    case Calculate =>
      while (true) {//Mine infinitely
        for (i <- 0 to 255)
          workerRouter ! WorkerWork(gatorID, textLength, i.toChar)
        textLength += 1
      }
    case Result(map: Map[String, String]) =>
      numOfWorkersDone += 1
      if (map != null && !map.isEmpty) {
        for (key <- map.keySet)
          resultMap + (key -> map.get(key).get)
      }

      if (numOfWorkersDone == jobs) {
        for (key <- resultMap.keySet)
          println(key + " , " + resultMap.get(key))
      }
  }
  
  def registerToSuperMaster() = {
    
  }
}