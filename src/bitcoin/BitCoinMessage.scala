package bitcoin

sealed trait BitCoinMessage
case class Calculate(gatorId: String, mask: Int) extends BitCoinMessage
case class RegisterToSuperMaster() extends BitCoinMessage
case class RegisterMaster(masterIP: String, port: Int)
case class WorkerWork(gatorID: String, textLength: Int, startChar: Char) extends BitCoinMessage
case class MasterWork(gatorID: String, textLength: Int) extends BitCoinMessage
case class Work(mask: Int, gatorID: String, seedLength: Int) extends BitCoinMessage
case class Result(map: Map[String, String]) extends BitCoinMessage
