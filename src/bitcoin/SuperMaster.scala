package bitcoin

import akka.actor.Actor
import akka.actor.Props
import akka.routing.RoundRobinRouter

class SuperMaster(mask: Int, gatorID: String) extends Actor {
 
  def receive = {
    case RegisterMaster => 
    case MasterWork => mineBitCoin()
    case Calculate => 
  }
  
  def mineBitCoin() = {
    
  }
}