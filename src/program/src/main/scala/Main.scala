package comparator

import comparator.Offer._
import FiltersCreator._

import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.model._
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._
import scala.collection.mutable.ListBuffer

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
    offer.isSuperSprzedawca = parser.superSprzedawcaParser(el)
    offer.price = parser.priceParser(el)
    offer.priceWithShipment = parser.priceWithShipmentParser(el)

    offers +=(offer)
  }

  // Prepare filters
  var filterChain = OfferFilterChain
  
  val filters = FiltersCreator.addFiltersToList(conf)
  
  val count: Int = conf.count.toOption match {
            case None => offers.length
            case Some(c: Int) => c
  }

  // Aplly filters
  var chain = filterChain.createFilterChain(filters.toList)

  println("Filtered offers:")
  offers.filter(chain)
        .take(count)
        .foreach(println)
}
