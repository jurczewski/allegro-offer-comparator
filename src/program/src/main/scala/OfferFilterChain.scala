package comparator

class OfferFilterChain{
    def createFilterChain(ps: List[Offer => Boolean]): Offer => Boolean = {
        val acc: Offer => Boolean = _ => true
        ps.foldLeft[Offer => Boolean](acc)(mergeFilter)
    }

    private def mergeFilter(f1: Offer => Boolean, f2: Offer => Boolean): Offer => Boolean = {
        a => f1(a) && f2(a)
    }
}
