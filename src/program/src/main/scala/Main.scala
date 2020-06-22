package comparator

import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.model._
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._

import scala.collection.mutable.ListBuffer
import comparator.Offer._
import filters._

object Main extends App {
  val conf = new Conf(args) // Load args from cmd

  val browser = JsoupBrowser()
  val query = conf.query()
  val doc = browser.get("https://allegro.pl/listing?string=" + query)

  val items = doc >> elementList("._9c44d_3pyzl article")
  val offers = new ListBuffer[Offer]()

  // Parse html to data
  val parser = OfferParser

  items.foreach { el =>
    var offer = new Offer()

    offer.title = parser.titleParser(el)
    offer.link = parser.linkParser(el)
    offer.state = parser.stateParser(el)
    offer.isSuperSprzedawca = parser.superSprzedawcaParser(el)
    offer.price = parser.priceParser(el)
    offer.priceWithShipment = parser.priceWithShipmentParser(el)

    offers +=(offer)
  }

  //Display all offers
  println("Downloaded offers:")
  offers.foreach(println)
  println("#"*100)

  // Prepare filters
  var filterChain = new OfferFilterChain()
  var filters = new ListBuffer[Offer => Boolean]

  if(conf.min.isSupplied){
    var min = new MinPriceFilter(conf.min())
    filters.+=(min.filter)
  }
  if(conf.max.isSupplied){
    var max = new MaxPriceFilter(conf.max())
    filters.+=(max.filter)
  }
  if(conf.shipmin.isSupplied){
    var minShipment = new MinPriceWithShipmentFilter(conf.shipmin())
    filters.+=(minShipment.filter)
  }
  if(conf.shipmax.isSupplied){
    var maxShipment = new MaxPriceWithShipmentFilter(conf.shipmax())
    filters.+=(maxShipment.filter)
  }
  if(conf.supers.isSupplied){
    var isSuperSprzedawca = new IsSuperSprzedawcaFilter()
    filters.+=(isSuperSprzedawca.filter)
  }

  // Aplly filters
  var chain = filterChain.createFilterChain(filters.toList)

  println("Filtered offers:")
  offers.filter(chain)
        .foreach(println)
}
