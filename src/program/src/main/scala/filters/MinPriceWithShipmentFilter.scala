package filters
import comparator.Offer

class MinPriceWithShipmentFilter(val minPrice: Double) extends Filter {
  override var filter: Offer => Boolean = o => {
    if(o.priceWithShipment > 0) o.priceWithShipment >= minPrice else true
  }
}
