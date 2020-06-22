package comparator

import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.model._

object Downloader{
    def downloadOffers(query: String): List[Element] = {
        val browser = JsoupBrowser()        
        val doc = browser.get("https://allegro.pl/listing?string=" + query)
        doc >> elementList("._9c44d_3pyzl article")
    }
}
