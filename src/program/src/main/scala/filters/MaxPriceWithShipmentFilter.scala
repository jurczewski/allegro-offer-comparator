package filters

import comparator.Offer

class MaxPriceWithShipmentFilter(val maxPrice: Double) extends Filter {
  override var filter: Offer => Boolean = o => {
    if(o.priceWithShipment > 0) o.priceWithShipment <= maxPrice else true
  }
}
