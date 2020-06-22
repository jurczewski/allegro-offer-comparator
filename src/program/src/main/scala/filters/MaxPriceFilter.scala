package filters

import comparator.{Filter, Offer}

class MaxPriceFilter(val maxPrice: Int) extends Filter {
    override var filter: Offer => Boolean = o => o.price <= maxPrice
}
