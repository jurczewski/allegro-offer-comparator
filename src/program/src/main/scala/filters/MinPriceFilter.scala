package filters
import comparator.Offer

class MinPriceFilter(val minPrice: Double) extends Filter {
  override var filter: Offer => Boolean = o => o.price >= minPrice
}
