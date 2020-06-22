package filters

import comparator.Offer

class MaxPriceFilter(val maxPrice: Double) extends Filter {
    override var filter: Offer => Boolean = o => o.price <= maxPrice
}
