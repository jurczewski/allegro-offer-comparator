package comparator

class OfferFilterChain{
    def createFilterChain(ps: List[Offer => Boolean]): Offer => Boolean = {
        val acc: Offer => Boolean = _ => true
        ps.foldLeft[Offer => Boolean](acc)(mergeFilter)
    }

    private def mergeFilter(f1: Offer => Boolean, f2: Offer => Boolean): Offer => Boolean = {
        a => f1(a) && f2(a)
    }
//
//    var wieksze5: Int => Boolean = el => el > 5
//    var podzielne3: Int => Boolean = el => el % 3 == 0
//    var mniejsze20: Int => Boolean = el => el < 20
//
//    val filters = List(wieksze5, podzielne3, mniejsze20)
//
//    val filterChain = createFilterChain(filters)
}
