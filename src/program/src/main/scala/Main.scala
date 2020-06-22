package comparator

object Main extends App {
  // Load args from cmd
  val conf = new Conf(args)

  //Download offers
  val query = conf.query()
  val items = Downloader.downloadOffers(query)
  val offers = OfferParser.parseElementsToOffers(items)

  // Prepare filters
  val filters = FiltersCreator.addFiltersToList(conf)
  val count: Int = conf.count.toOption match {
            case None => offers.length
            case Some(c: Int) => c
  }
  
  // Apply filters and print results
  var chain = OfferFilterChain.createFilterChain(filters.toList)

  println("Filtered offers:")
  offers.filter(chain)
        .take(count)
        .foreach(println)
}
