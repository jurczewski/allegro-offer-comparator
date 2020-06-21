package comparator

class Offer(var title: String = "", 
            var link: String = "",
            var state: String = "",
            var isSuperSprzedawca: Boolean = false,
            var price: Double = 0.0,
            var priceWithShipment: Double = 0.0)
{
  override def toString: String =
      s"Title: $title | state: $state | isSuperSprzedawca: $isSuperSprzedawca | price: $price zl | priceWithShipment: $priceWithShipment zl"
}