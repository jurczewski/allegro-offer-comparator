package comparator

import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.model._
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._
import scala.collection.mutable.ListBuffer

import comparator.Offer._

object Main extends App {
  val browser = JsoupBrowser()

  val query = "monitor"
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
  offers.foreach(println)

  // Filter
  val conf = new Conf(args)
  if(conf.bananas.isSupplied) println("apples are: " + conf.apples())
  println("bannanas: " + conf.bananas.isSupplied)
}