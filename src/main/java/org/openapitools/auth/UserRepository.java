package org.openapitools.auth;

import java.util.List;
import java.util.Optional;

import org.openapitools.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findAll();
	Optional<User> findUserByUserName(String userName);
	Optional<User> findUserByUserNameAndPassword(String userName, String password);
}
