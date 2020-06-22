package filters
import comparator.Offer

class MaxPriceWithShipmentFilter(val maxPrice: Double) extends Filter {
  override var filter: Offer => Boolean = o => o.priceWithShipment <= maxPrice
}
