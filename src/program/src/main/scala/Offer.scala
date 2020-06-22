package comparator

class Offer(var title: String = "", 
            var link: String = "",
            var isSuperSprzedawca: Boolean = false,
            var price: Double = -1.0,
            var priceWithShipment: Double = -1.0)
{
  override def toString: String = {
    val result: StringBuilder = new StringBuilder("")
    val separator: String = "\n" + "#"*100
    val titleString: String = s"\n# Title: $title"
    val superSprzedawca: String = if(isSuperSprzedawca) "\n# SuperSprzedawca" else ""
    var priceString: String = s"\n# Price: $price zl"
    priceString = priceString.concat(if(priceWithShipment > 0) s" ($priceWithShipment zl with shipment)" else "")
    val linkString = s"\n# Link: $link"
    result + separator + titleString + superSprzedawca + priceString + linkString // + separator
  }
}
