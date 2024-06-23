package com.ecom.repositories;


import com.ecom.model.UserDtls;
import org.springframework.data.jpa.repository.JpaRepository;


public interface userepository extends JpaRepository<UserDtls, Integer> {
    public UserDtls findByEmail(String email);

}