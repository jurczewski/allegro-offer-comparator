package comparator

object Main extends App {
  val conf = new Conf(args) // Load args from cmd

  val query = conf.query()
  val items = Downloader.downloadOffers(query)

  val offers = OfferParser.parseElementsToOffers(items)

  // Prepare filters
  val filters = FiltersCreator.addFiltersToList(conf)
  val count: Int = conf.count.toOption match {
            case None => offers.length
            case Some(c: Int) => c
  }
  
  // Aplly filters
  var chain = OfferFilterChain.createFilterChain(filters.toList)

  println("Filtered offers:")
  offers.filter(chain)
        .take(count)
        .foreach(println)
}
