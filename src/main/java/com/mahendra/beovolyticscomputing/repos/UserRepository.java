package com.mahendra.beovolyticscomputing.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.mahendra.beovolyticscomputing.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("select u from User u where u.username = :username")
	User findUserByUsername(String username);

	User findByUsername(String username);

	@Transactional
	@Modifying
	@Query("update User u set lastLogin=currentLogin, currentLogin=now() where u.username = :username")
	void updateLastLogin(String username);
}
