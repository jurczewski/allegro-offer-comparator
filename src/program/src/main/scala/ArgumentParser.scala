package comparator

import org.rogach.scallop._

class Conf(arguments: Seq[String]) extends ScallopConf(arguments) {
  val query = opt[String](required = true, descr="Query to search for")
  val min = opt[Int](descr="Minimum price")
  val max = opt[Int](descr="Maximum price")
  val shipmax = opt[Int](descr="Maximum price with shipment")
  val shipmin = opt[Int](descr="Minimum price with shipment")
  val supers = opt[Boolean](descr="Flag: Switch to seach for 'SuperSprzedawca'")
  val count = opt[Int](descr="How many elements to return")

  verify()
}