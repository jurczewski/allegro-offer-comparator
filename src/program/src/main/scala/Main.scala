import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.model._
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._
import scala.collection.mutable.ListBuffer

object Main extends App {
  val browser = JsoupBrowser()

  val query = "monitor"
  val doc = browser.get("https://allegro.pl/listing?string=" + query)

  val items = doc >> elementList("._9c44d_3pyzl article")
  val offers = new ListBuffer[Offer]()  

  items.foreach { el =>
    var offer = new Offer();

    offer.desc = el >> text("._9c44d_LUA1k")
    offer.price = el >> text("._9c44d_1zemI") //todo: parse to dobule

    //SuperSprzedawca
    val superSprzedawcaText = el >?> text("._9c44d_38vuk")
    val superSprzedawcaValue: String = superSprzedawcaText match {
      case None => ""//Or handle the lack of a value another way: throw an error, etc.
      case Some(s: String) => s //return the string to set your value
    }
    offer.isSuperSprzedawca =  if(superSprzedawcaValue.contains("Super Sprzedawcy")) true else false

    offers +=(offer)
   }

   //Display all offers
   offers.foreach(println)
}

class Offer(var id: Int = 0,
            var desc: String = "",
            var link: String = "",
            var title: String = "",
            var state: String = "",
            var isSuperSprzedawca: Boolean = false,
            var price: String = "",
            var priceWithShipment: Double = 0.0)
{
  override def toString: String =
      s"Id: $id, Desc: $desc, link: $link, title: $title, state: $state, isSuperSprzedawca: $isSuperSprzedawca, price: $price, priceWithShipment: $priceWithShipment"
}