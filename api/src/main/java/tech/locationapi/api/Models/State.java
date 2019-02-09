package tech.locationapi.api.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "US_STATES")
@Getter @Setter
@NoArgsConstructor
@ToString
public class State {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "STATE_CODE")
    private String stateCode;

    @Column(name = "STATE_NAME")
    private String stateName;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,
               fetch = FetchType.LAZY,
               mappedBy = "state")
    private Set<City> cities = new HashSet<>();

    public void addCities(City city) {
        this.cities.add(city);
        city.setState(this);
    }

    public void removeCities(City city) {
        this.cities.remove(city);
        city.setState(null);
    }

}
