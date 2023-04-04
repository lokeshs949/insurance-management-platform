package com.assignment.insurance.repo;

import com.assignment.insurance.model.Client;
import com.assignment.insurance.model.InsurancePolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PolicyRepository extends JpaRepository<InsurancePolicy,Long> {

    Optional<InsurancePolicy> findInsurancePolicyById(Long id);
}
