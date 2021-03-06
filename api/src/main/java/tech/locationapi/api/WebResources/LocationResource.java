package tech.locationapi.api.WebResources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.locationapi.api.Services.LocationService;

import java.util.Map;

/**
 *  Rest Controller with allowing users to get info from DB
 */
@RestController
public class LocationResource {

    private final LocationService service;

    public LocationResource(LocationService service) {
        this.service = service;
    }


    /**
     * Endpoint to get all states or all cities
     * @param district must be "cities" or "states" to choose what resource to get
     * @return all states or all cities
     */
    @GetMapping("/api/all/")
    public ResponseEntity<?> listAll(@RequestParam String district) {
        if (!district.toLowerCase().equals("cities") && !district.toLowerCase().equals("states")) {
            throw new Error("Parameter misspelled");
        }
        if (district.toLowerCase().equals("cities")) {
            return new ResponseEntity<>(service.getAllCities(), HttpStatus.OK);
        }else
            return new ResponseEntity<>(service.getAllStates(), HttpStatus.OK);
    }


    /**
     *  Endpoint to get one state(plus cities for that state) or one city
     * @param state can be abbreviation or full name
     * @param city (optional) selects the city to return information for if no
     *             city is selected will return state information
     * @return state object or city object in JSON format
     */
    @GetMapping("/api/getone/")
    public ResponseEntity<?> getStates(@RequestParam String state, @RequestParam(required = false) String city) {
        if (state.length()<2){
            throw new Error("Misspelled state");
        }
        try {
        if (city != null && state != null) {
            return new ResponseEntity<>(service.getCityFromState(state, city), HttpStatus.OK);
        }
            return new ResponseEntity<>(service.getState(state), HttpStatus.OK);
        } catch (Exception e) {
            throw new Error("Item not found or misspelled");
        }
    }


    /**
     *  Endpoint to get lat/ Long of selected city
     * @param state can be abbreviation or full name
     * @param city used to select city from state
     * @return lat/long of selected city in JSON format
     */
    @GetMapping("/api/getlocation/")
    public ResponseEntity<?> getGeolocation(@RequestParam String state, @RequestParam String city) {
        if (state.length()<2){
            throw new Error("Misspelled state");
        }
        return new ResponseEntity<Map>(service.getCityLocationFromState(state, city), HttpStatus.OK);
    }
}
