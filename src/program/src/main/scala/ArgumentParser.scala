package comparator

import org.rogach.scallop._

class Conf(arguments: Seq[String]) extends ScallopConf(arguments) {
  // val apples = opt[Int](required = true)
  val apples = opt[Int]()
  val bananas = opt[Int]()
  // val name = trailArg[String]()
  verify()
}