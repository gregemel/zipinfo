# Zip Info Service
* provide US zip code
* get back temperature, time zone, and elevation
* uses:
    * Open WeatherMap
    * Google Time Zone
    * Google Elevation
    
    * for Cayuse code challenge 

## sample usage:
* enter proper api keys for Open Weather and Google in application.properties
* run service on port 8080 (default)
* using browser:
    * http://localhost:8080/api/v1/zipinfo/97201
    * http://localhost:8080/api/v1/zipinfo/10101
    
* results:
    * At the location Portland, the temperature is 46F, the timezone is Pacific Standard Time, and the elevation is 11.0ft.
    * At the location New York, the temperature is 46F, the timezone is Eastern Standard Time, and the elevation is 45.0ft.
        
## what's missing?
* security
* functional/integration tests
* gui