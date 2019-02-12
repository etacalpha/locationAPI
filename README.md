# Welcome to the Location API http://locationapi.tech
This API returns location information about cities in the U.S. There are three endpoints to limit the amount of data returned. Check out the swagger docs

### The first endpoint is http://locationapi.tech/api/all
It will return either all the states and their cities or return all the cities. The request parameter for this end point is "district". You can search by states or cities.

http://locationapi.tech/api/all/?district=states


### The second endpoint is http://locationapi.tech/api/getone/
It will return either one state and it's cities or one city. The request parameters for this end point are "state"(this can be abbreviation or full name, "city. The city parameter is optional.

http://locationapi.tech/api/getone/?state=al&city=Abbeville


### The final endpoint is http://locationapi.tech/api/getlocation/
It will return either one state and it's cities or one city. The request parameters for this end point are "state"(this can be abbreviation or full name), "city parameter is NOT optional on this end point.

http://locationapi.tech/api/getlocation/?state=al&city=Abbeville

I found the db on Kelvin S. do Prado's github I did not build it so he did most the work.
https://github.com/kelvins/US-Cities-Database
