package com.revature.data;

import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    Optional<User> findUserByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);

    // returns all the users ordered by last name
//    List<User> findByOrderByLastName();

    // custom query
//    @Query("From User WHERE email LIKE %:pattern") // the : is a placeholder for the argument
//    List<User> findByEmailContains(String pattern); // johnsmi -> returns johnsmith@gmail.com
}
