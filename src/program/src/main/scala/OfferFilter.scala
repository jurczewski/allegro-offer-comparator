package comparator

object OfferFilter{
    def podzielne2(i: Int): Boolean = i % 2 == 0
    def podzielne3(i: Int): Boolean = i % 3 == 0

    val nums = List.range(1, 20)

    val res = nums.filter(podzielne2 _)
                    .filter(podzielne3 _)

    println(res)
}