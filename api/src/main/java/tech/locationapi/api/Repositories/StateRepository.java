package tech.locationapi.api.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.locationapi.api.Models.State;



@Repository
public interface StateRepository extends JpaRepository<State, Long> {

     State getStateByStateCode(String name);
     State getStateByStateNameIgnoreCase(String name);

}
