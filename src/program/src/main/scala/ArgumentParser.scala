package comparator

import org.rogach.scallop._

class Conf(arguments: Seq[String]) extends ScallopConf(arguments) {
  val query = opt[String](required = true)
  val below = opt[Int]()
  val above = opt[Int]()
  val shipbelow = opt[Int]()
  val shipabove = opt[Int]()
  val supers = opt[Boolean]()
  val count = opt[Int]()

  verify()
}