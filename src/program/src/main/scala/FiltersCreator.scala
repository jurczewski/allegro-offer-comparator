package comparator

import scala.collection.mutable.ListBuffer
import filters._

object FiltersCreator{
    def addFiltersToList(conf: Conf): ListBuffer[Offer => Boolean] = {
        var filters = new ListBuffer[Offer => Boolean]

        if(conf.min.isSupplied){
            val min = new MinPriceFilter(conf.min())
            filters.+=(min.filter)
        }
        if(conf.max.isSupplied){
            val max = new MaxPriceFilter(conf.max())
            filters.+=(max.filter)
        }
        if(conf.shipmin.isSupplied){
            val minShipment = new MinPriceWithShipmentFilter(conf.shipmin())
            filters.+=(minShipment.filter)
        }
        if(conf.shipmax.isSupplied){
            val maxShipment = new MaxPriceWithShipmentFilter(conf.shipmax())
            filters.+=(maxShipment.filter)
        }
        if(conf.supers.isSupplied){
            val isSuperSprzedawca = new IsSuperSprzedawcaFilter()
            filters.+=(isSuperSprzedawca.filter)
        }
        filters
    }
}
