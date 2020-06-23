# allegro-offer-comparator
Simple Allegro offer comparator written completely in Scala.

# How to run comparator
* Launch sbt
* Use `run --help` to check available parameters
* Use `run --{arg} {value}` to lauch the comparator with different parameters

# Available parameters:
### Required:
* query - Query to search for &nbsp;&nbsp; | &nbsp;&nbsp; Notice:  **remember to use parentheses**

### Comparator filters:
* min - Minimum price
* max - Maximum price
* shipmin - Minimum price with shipment
* shipmax - Maximum price with shipment
* supers - Flag: Switch to seach for 'SuperSprzedawca'
* count - How many elements to return