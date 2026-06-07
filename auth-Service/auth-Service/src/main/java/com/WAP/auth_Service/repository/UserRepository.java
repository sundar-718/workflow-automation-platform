package com.WAP.auth_Service.repository;


import com.WAP.auth_Service.dto.loginResponse;
import com.WAP.auth_Service.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<user,Long> {

    @Query("""
        SELECT new com.WAP.auth_Service.dto.loginResponse(
            u.password
        )
        FROM user u WHERE u.name= :userName
    """)
    loginResponse finduserByName(String userName);

    boolean existsByEmail(String email);
}
