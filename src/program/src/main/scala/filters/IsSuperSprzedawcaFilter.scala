package filters

import comparator.Offer

class IsSuperSprzedawcaFilter extends Filter {
  override var filter: Offer => Boolean = o => o.isSuperSprzedawca
}
