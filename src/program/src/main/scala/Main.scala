import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.model._
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._

object Main extends App {
  val browser = JsoupBrowser()

  val query = "monitor"
  val doc = browser.get("https://allegro.pl/listing?string=" + query)

  var offer = new Offer();
  offer.desc = doc >> text("._9c44d_1LBF0")

  val items = doc >> elementList("._9c44d_3pyzl article")

  // From each item, extract all the text inside their <a> elements
  items.map(_ >> allText("a"))

  println(offer.desc)

  items.foreach { println }
}

class Offer(var id: Int = 0,
            var desc: String = "",
            var link: String = "",
            var title: String = "",
            var state: String = "",
            var isSuperSprzedawca: Boolean = false,
            var price: Double = 0.0,
            var priceWithShipment: Double = 0.0)
{

}