package tech.locationapi.api.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="US_CITIES")
@Getter @Setter
@NoArgsConstructor
@ToString
public class City {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="ID_STATE",insertable = false, updatable = false)
    private String states_ID ;

    @Column(name="CITY")
    private String city;

    @Column(name = "COUNTY")
    private String county;

    @Column(name = "LATITUDE")
    private double latitude;

    @Column(name = "Longitude")
    private double longitude;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_STATE", nullable = false)
    private State state;
}
