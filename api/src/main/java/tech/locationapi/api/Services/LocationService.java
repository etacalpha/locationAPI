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

@Service
public class LocationService {

    private final StateRepository state;
    private final CityRepository city;


    public LocationService(StateRepository state, CityRepository city) {
        this.state = state;
        this.city = city;
    }


    /**
     *
     * @return
     */
    public ArrayList<State> getAllStates() {
        return (ArrayList)state.findAll();
    }

    /**
     *
     * @return
     */
    public List<City> getAllCities() { return city.findAll();}


    /**
     *
     * @param name
     * @return
     */
    public State getState(String name) {
        State state= new State();
        try {
            if (name.length()==2) {
                state = this.state.getStateByStateCode(name.toUpperCase());
                System.out.println("Get by code!");
            }else {
                state = this.state.getStateByStateNameIgnoreCase(name.toUpperCase());
                System.out.println("Get by Name!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return state;
    }


    /**
     *
     * @param state
     * @param city
     * @return
     */
    public City getCityFromState(String state, String city){
        State stateName;
        City foundCity = new City();
        try {
            stateName=getState(state);
            ArrayList<City> cities = new ArrayList<>(stateName.getCities());
            for (City item:cities) {
                if (item.getCity().toLowerCase().equals(city.toLowerCase())){
                    foundCity=item;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return location;
    }
}
