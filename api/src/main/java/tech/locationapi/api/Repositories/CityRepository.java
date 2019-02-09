package tech.locationapi.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tech.locationapi.api.Models.City;



@RepositoryRestResource
public interface CityRepository extends JpaRepository<City, Long> {


}
