package filters

import comparator.Offer

trait Filter {
    var filter: Offer => Boolean
}
