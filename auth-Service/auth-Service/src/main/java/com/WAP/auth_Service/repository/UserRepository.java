package com.WAP.auth_Service.repository;


import com.WAP.auth_Service.model.user;
import org.springframework.data.jpa.repository.JpaRepository;




public interface UserRepository extends JpaRepository<user,Long> {

}
