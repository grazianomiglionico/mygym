package it.corso.mygym.dao;

import it.corso.mygym.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
    The JpaRepository extends the PagingAndSortingRepository which extends the CrudRepository.
*/

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // TODO: find by activeFlag=true

    // TODO: find by activeFlag=true AND have an active subscription --> @query
}
