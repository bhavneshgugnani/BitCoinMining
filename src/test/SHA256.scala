package test

import java.security.MessageDigestSpi._

object SHA256 {
  private val sha = java.security.MessageDigest.getInstance("SHA-256")
  
  def main(args: Array[String]) {
      println(hex_digest("bhavnesh.gugnani÷"))
    val md = java.security.MessageDigest.getInstance("SHA-256")
val ha = new sun.misc.BASE64Encoder().encode(md.digest("bhavnesh.gugnani÷".getBytes("UTF-8")))
println(md.digest("bhavnesh.gugnani÷".getBytes("UTF-8")))
println(ha)
  }

  def hex_digest(s: String): String = {
    sha.digest(s.getBytes("UTF-8"))
      .foldLeft("")((s: String, b: Byte) => s +
        Character.forDigit((b & 0xf0) >> 4, 16) +
        Character.forDigit(b & 0x0f, 16))
  }
}