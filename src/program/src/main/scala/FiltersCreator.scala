package comparator

import scala.collection.mutable.ListBuffer
import filters._

object FiltersCreator{
    def addFiltersToList(conf: Conf): ListBuffer[Offer => Boolean] = {
        var filters = new ListBuffer[Offer => Boolean]

        if(conf.min.isSupplied){
            var min = new MinPriceFilter(conf.min())
            filters.+=(min.filter)
        }

        if(conf.max.isSupplied){
            var max = new MaxPriceFilter(conf.max())
            filters.+=(max.filter)
        }

        if(conf.shipmin.isSupplied){
            var minShipment = new MinPriceWithShipmentFilter(conf.shipmin())
            filters.+=(minShipment.filter)
        }

        if(conf.shipmax.isSupplied){
            var maxShipment = new MaxPriceWithShipmentFilter(conf.shipmax())
            filters.+=(maxShipment.filter)
        }

        if(conf.supers.isSupplied){
            var isSuperSprzedawca = new IsSuperSprzedawcaFilter()
            filters.+=(isSuperSprzedawca.filter)
        }

        filters
    }
}
