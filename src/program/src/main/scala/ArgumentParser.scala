package comparator

import org.rogach.scallop._

class Conf(arguments: Seq[String]) extends ScallopConf(arguments) {
  val query = opt[String](required = true)
  val min = opt[Int]()
  val max = opt[Int]()
  val shipmax = opt[Int]()
  val shipmin = opt[Int]()
  val supers = opt[Boolean]()
  val count = opt[Int]()

  verify()
}