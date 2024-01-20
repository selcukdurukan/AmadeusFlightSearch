package com.amadeus.repository;

import com.amadeus.repository.entity.Auths;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAuthRepository extends JpaRepository<Auths, Long> {

    Optional<Auths> findOptionalByEmail(String email);

    Optional<Auths> findOptionalByEmailAndPassword(String email, String password);
}
