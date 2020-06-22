package comparator

import net.ruippeixotog.scalascraper.model._
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._

object OfferParser {

    val titelClass = "._9c44d_LUA1k"
    val superSprzedawcaClass = "._9c44d_38vuk"
    val priceClass = "._9c44d_1zemI"
    val priceWithShipmentClass = "._9c44d_21XN-"

    def titleParser(el: Element): String = {
        el >> text(titelClass)
    }

    def priceWithShipmentParser(el: Element): Double = {
        val priceDiv = el >?> element(priceWithShipmentClass)
        val list = priceDiv >> elementList("i")

        list match {
            case Some(_) => {
                val first = list.get(0)
                val firstText = first >> text

                if(firstText.contains("darmowa")){
                    return priceParser(el)
                } else{
                    return parseDouble(firstText) match {
                        case None => 0.0
                        case Some(d: Double) => d
                    }
                }
            }
            case None => 0.0
        }
    }

    def linkParser(el: Element): String = {
         el >> attr("href")("a")
    }

    def superSprzedawcaParser(el: Element): Boolean = {
        val superSprzedawcaText = el >?> text(superSprzedawcaClass)
        val superSprzedawcaValue: String = superSprzedawcaText match {
            case None => ""
            case Some(s: String) => s
        }

        if(superSprzedawcaValue.contains("Super Sprzedawcy")) true else false
    }

    def priceParser(el: Element): Double = {
        val price: String = el >> text(priceClass)
        parseDouble(price) match {
            case None => 0.0
            case Some(p: Double) => p
        }
    }

    def parseDouble(str: String) = try { 
        var s = trimWhitespaces(str)
        s = removeCurrency(s)
        s = s.replaceAll(",", ".") 
        Some(s.toDouble) 
    } catch { case _ : Throwable => None }

    private def removeCurrency(s: String) = {
        s.slice(0, s.length() - 2)
    }

    private def trimWhitespaces(str: String) = {
        str.replaceAll("\\s", "")
    }
}
