package com.demo.jwt.repository;

import com.demo.jwt.entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface LoginRepository extends JpaRepository<LoginEntity,Long>{

    @Query("select l from LoginEntity l where l.userName = :username and l.password = :password")
    Optional<LoginEntity> findByUserNameAndPassword(@Param("username") String username, @Param("password") String password);

}
