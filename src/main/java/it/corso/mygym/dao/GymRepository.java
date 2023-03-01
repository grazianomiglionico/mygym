package it.corso.mygym.dao;

import it.corso.mygym.model.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
    The JpaRepository extends the PagingAndSortingRepository which extends the CrudRepository.
*/

@Repository
public interface GymRepository extends JpaRepository<Gym, Long> {
}
