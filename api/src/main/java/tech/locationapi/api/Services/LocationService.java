package tech.locationapi.api.Services;


import org.springframework.stereotype.Service;
import tech.locationapi.api.Models.City;
import tech.locationapi.api.Models.State;
import tech.locationapi.api.Repositories.CityRepository;
import tech.locationapi.api.Repositories.StateRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Service to send queries to repository (state, city)
 */
@Service
public class LocationService {

    private final StateRepository state;
    private final CityRepository city;


    public LocationService(StateRepository state, CityRepository city) {
        this.state = state;
        this.city = city;
    }


    /**
     * calls state repository
     * @return list of all states
     */
    public ArrayList<State> getAllStates() {
        return (ArrayList)state.findAll();
    }

    /**
     * calls city repository
     * @return list of all cities
     */
    public List<City> getAllCities() { return city.findAll();}


    /**
     *calls state repository
     * @param name can be full name or abbreviation
     * @return state object
     */
    public State getState(String name) throws Exception {
        State state= new State();
            if (name.length()==2) {
                state = this.state.getStateByStateCode(name.toUpperCase());

                System.out.println("Get by code!");
            }else {
                state = this.state.getStateByStateNameIgnoreCase(name.toUpperCase());
                System.out.println("Get by Name!");
            }
            if (state.getId()==null){
                throw new Error("State does not exist");
            }
        return state;
    }


    /**
     * calls state repository
     * @param state used to limit cities to a state
     * @param city used to select a city from state object
     * @return returns a city from the state object
     */
    public City getCityFromState(String state, String city) throws Exception {
        State stateName;
        City foundCity = new City();
            stateName=getState(state);
        if (stateName.getId()==null){
            throw new Error("State does not exist or is misspelled");
        }
            ArrayList<City> cities = new ArrayList<>(stateName.getCities());
            for (City item:cities) {
                if (item.getCity().toLowerCase().equals(city.toLowerCase())){
                    foundCity=item;
                }
            }
        if (foundCity.getId()==null){
            throw new Error("City does not exist or is misspelled");
        }
        return foundCity;
    }

    /**
     * Get the city location
     * @param state required to limit multiple cities
     * @param city
     * @return
     */
    public Map<String,Double> getCityLocationFromState(String state, String city){
        Map<String,Double>location = new HashMap<>();
        try {
            City foundCity = getCityFromState(state, city);
            location.put("Latitude",foundCity.getLatitude());
            location.put("Longitude",foundCity.getLongitude());
        }catch (Exception e){
            throw new Error("State or City does not exist!");
        }
        return location;
    }
}
