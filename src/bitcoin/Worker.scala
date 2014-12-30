package bitcoin

import akka.actor.Actor
import scala.util.matching.Regex
import scala.collection.immutable.HashMap

class Worker(mask: Int) extends Actor {
  private val sha = java.security.MessageDigest.getInstance("SHA-256")
  val pattern = "(^0{" + mask + "}.*)"

  def receive = {
    case WorkerWork(gatorId, textLength, startChar) => sender() ! Result(check(gatorId + startChar, textLength - 1))
  }

  def check(str: String, textLength: Int) = {
    val resultMap: Map[String, String] = Map()

    generateString(str, textLength, 0, resultMap)

    if (!resultMap.isEmpty)
      resultMap
    else
      null
  }

  def hex_digest(s: String): String = {
    sha.digest(s.getBytes("UTF-8"))
      .foldLeft("")((s: String, b: Byte) => s +
        Character.forDigit((b & 0xf0) >> 4, 16) +
        Character.forDigit(b & 0x0f, 16))
  }

  def generateString(str: String, textLength: Int, currIndex: Int, resultMap: Map[String, String]): Unit = {
    if (currIndex == textLength) {
      val hash = hex_digest(str.toString())
      try {
        val regex = pattern.r
        val regex(value) = hash
        if (value != "") {
          resultMap + (str -> value)
          println(str + " , " + value)
        }
      } catch {
        case io: scala.MatchError => //No match found for mask bits in hash value 
      }
    } else if (currIndex < textLength) {
      for (i <- 0 to 255)
        generateString(str + i.toChar, textLength, currIndex + 1, resultMap)
    }
  }

}
