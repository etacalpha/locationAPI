package tech.locationapi.api.WebResources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.locationapi.api.Services.LocationService;

import java.util.Map;

@RestController
public class LocationResource {

    private final LocationService service;

    public LocationResource(LocationService service) {
        this.service = service;
    }


    @GetMapping("/api/all/")
    public ResponseEntity<?> listAll(@RequestParam String district) {
        if (district.toLowerCase().equals("cities")) {
            return new ResponseEntity<>(service.getAllCities(), HttpStatus.OK);

        }
        if (district.toLowerCase().equals("states")) {
            return new ResponseEntity<>(service.getAllStates(), HttpStatus.OK);

        } else
            return new ResponseEntity<>("ERROR BAD REQUEST", HttpStatus.BAD_REQUEST);

    }


    @GetMapping(value = "/api/getState/")
    public ResponseEntity<?> getStates(@RequestParam String state) {
        try {
            return new ResponseEntity<>(service.getState(state), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error with request", HttpStatus.BAD_REQUEST);
    }


    @GetMapping(value = "/api/getCity/")
    public ResponseEntity<?> getCities(@RequestParam String state, @RequestParam(required = false) String city) {
        if (city != null && state != null) {
            try {
                return new ResponseEntity<>(service.getCityFromState(state, city), HttpStatus.OK);

            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>("Error with request", HttpStatus.BAD_REQUEST);
            }
        } else if (city == null && state != null) {
            try {
                return new ResponseEntity<>(service.getState(state), HttpStatus.OK);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>("Error with request", HttpStatus.BAD_REQUEST);
    }


    @GetMapping(value = "/api/getGeoLocation/")
    public ResponseEntity<?> getGeolocation(@RequestParam String state, @RequestParam String city) {
        if (state == null || city == null) {
            return new ResponseEntity<>("You forgot something", HttpStatus.BAD_REQUEST);
        }
        try {
            return new ResponseEntity<Map>(service.getCityLocationFromState(state, city), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
    }
}
