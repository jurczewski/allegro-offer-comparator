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
  
  items.foreach { el =>
    var offer = new Offer()
    val parser = OfferParser

    offer.desc = parser.descriptionParser(el)
    offer.price = parser.priceParser(el)
    offer.isSuperSprzedawca = parser.superSprzedawcaParser(el)
    //offer.title = el >> text("._9c44d_3TzmE") //todo: remove 'Oferta sponsorowana'

    offers +=(offer)
   }

   //Display all offers
   offers.foreach(println)
}

class Offer(var desc: String = "",
            var link: String = "",
            var title: String = "",
            var state: String = "",
            var isSuperSprzedawca: Boolean = false,
            var price: Double = 0.0,
            var priceWithShipment: Double = 0.0)
{

  override def toString: String =
      s"Desc: $desc | link: $link | title: $title | state: $state | isSuperSprzedawca: $isSuperSprzedawca | price: $price zł | priceWithShipment: $priceWithShipment zł"
}