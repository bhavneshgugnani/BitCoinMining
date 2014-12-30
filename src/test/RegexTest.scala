package test

object RegexTest {
  def main(args: Array[String]) {
    val regex = "(^0{4,}.*)".r
    val regex(hash) = "00009hg"
    println(hash != "")
  }

}