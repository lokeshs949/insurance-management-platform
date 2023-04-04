package com.assignment.insurance.repo;

import com.assignment.insurance.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClaimRepository extends JpaRepository<Claim , Long> {

    Optional<Claim> getClaimById(Long id);


}
