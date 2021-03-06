# Zip Info Service - Java Spring Boot -- http endpoints

* provides temperature, time zone, and elevation data for given us zip code
* demonstrates hexagonal, http spring boot, endpoint with core, models, ports, and adapters namespaces

* Swagger:
    * http://localhost:8080/swagger-ui.html#/

* uses:
    * Open WeatherMap
    * Google Time Zone
    * Google Elevation
    
    * java spring boot -- http endpoints
    * http controller and clients with unit tests
    
    <img src="./ZipInfo%20Hexagonal%20Architecture.svg">
    

## sample usage:
* configure api-keys for Open Weather and Google
   * application.properties
* run service (default: port 8080)
* using browser:
    * http://localhost:8080/api/v1/zipinfo/97201
    * http://localhost:8080/api/v1/zipinfo/10101
    
* results:
    * At the locationConditions Portland, the temperature is 44F, the timezone is Pacific Standard Time, and the elevation is 11.0ft.
    * At the locationConditions New York, the temperature is 46F, the timezone is Eastern Standard Time, and the elevation is 45.0ft.
        
## what's missing?
* security
* functional/integration tests/mocking
* caching
* logging/monitoring/health
* build/deploy pipeline
* gui
