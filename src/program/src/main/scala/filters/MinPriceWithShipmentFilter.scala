package filters
import comparator.Offer

class MinPriceWithShipmentFilter(val minPrice: Double) extends Filter {
  override var filter: Offer => Boolean = o => o.priceWithShipment >= minPrice
}
