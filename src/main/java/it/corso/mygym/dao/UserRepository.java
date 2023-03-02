package it.corso.mygym.dao;

import it.corso.mygym.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
    The JpaRepository extends the PagingAndSortingRepository which extends the CrudRepository.
*/

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByActiveFlagTrue();

    @Query(value = "SELECT u " +
            "FROM User u, Subscription s " +
            "WHERE u.activeFlag = true AND " +
            "u.id = s.user.id AND s.endDate >= CURRENT_DATE " +
            "GROUP BY u.id")
    List<User> findByActiveFlagTrueAndActiveSubscription();
}
