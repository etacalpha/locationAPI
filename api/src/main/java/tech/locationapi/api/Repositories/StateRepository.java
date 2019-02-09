package tech.locationapi.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tech.locationapi.api.Models.State;



@RepositoryRestResource
public interface StateRepository extends JpaRepository<State, Long> {

     State getStateByStateCode(String name);
     State getStateByStateNameIgnoreCase(String name);

}
