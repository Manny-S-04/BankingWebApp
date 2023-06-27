package com.bank.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bank.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    
    @Query("SELECT u FROM User u WHERE u.email = :email")
    public User findEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
    public User login(@Param("email") String email, @Param("password") String password);

    public User save(User user);
    
    
}
