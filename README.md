# myretail api #

## Introduction ##
URL: /products is the restful style API . It will provide information about products such as UniqueID, Name/Description and Pricing Information. 

Here are some of the ways to interact with this API:

Http GET
/products/{productID}

Http POST
/products
BODY: 
Type: JSON
Value: {"id":13860428,"name":"The Big Lebowski (Blu-ray) (Widescreen)","current_price":{"value": 13.49,"currency_code":"USD"}}


Http DELETE
/products/{productId}

## Setup dev environment ##

Pre-requisite
MongoDB
JDK16
Gradle tool/plugin

* Download code: git clone https://github.com/nickspat/myretail.git
* configure mongodb
    * Add collection with name: price
    * Add document(s) in the collection "price" e.g. {"productId":"121211", "value":123.12, "currencyType":"USD"}

* Update application.properties
    * change hostname/port/database name for mongodb if the values are different for your environment

* Run gradle: ./gradlew run

## Tips for future enhancements ##