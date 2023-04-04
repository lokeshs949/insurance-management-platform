package com.assignment.insurance.controller;

import com.assignment.insurance.exception.ResourceNotFoundException;
import com.assignment.insurance.model.Claim;
import com.assignment.insurance.model.Client;
import com.assignment.insurance.model.InsurancePolicy;
import com.assignment.insurance.repo.ClientRepository;
import com.assignment.insurance.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path="/api")
public class PolicyController {

private final PolicyService policyService;
    private final ClientRepository clientRepository;
    @Autowired
    public PolicyController(PolicyService policyService, ClientRepository clientRepository) {
        this.policyService = policyService;
        this.clientRepository=clientRepository;
    }

    @GetMapping(path = "/policies")
    public List<InsurancePolicy> getPolicies(){
        return policyService.getAllPolicies();
    }
    @GetMapping(path = "/policy/{id}")
    public Optional<InsurancePolicy> getPolicyById(@PathVariable(value="id") Long id){
        return policyService.getInsurancePolicyById(id);
    }

    @PostMapping(path = "/policy")
    public InsurancePolicy createPolicy(@RequestBody InsurancePolicy insurancePolicy){
   //  insurancePolicy.setClient(clientRepository.findClientById(insurancePolicy.getClient_id()));

        //this will get the client with the id provided in the input response body
        Optional<Client> optionalClient =clientRepository.findClientById(insurancePolicy.getClient_id());
        Client client = optionalClient.map(c -> {
            return new Client(c.getId(),c.getName(), c.getDob(), c.getAddress(),c.getContact());
        }).orElseThrow(() -> new ResourceNotFoundException("Client not found with id: "));
        insurancePolicy.setClient(client);

        return policyService.createPolicy(insurancePolicy);
    }

    @PutMapping(path = "/Upolicy/{id}")
    public ResponseEntity<InsurancePolicy> updatePolicy(@PathVariable long id, @RequestBody InsurancePolicy insurancePolicy){
        InsurancePolicy updateInsurancePolicy = policyService.getInsurancePolicyById(id).orElseThrow(() -> new ResourceNotFoundException("Client not exist with id: " + id));

        if (insurancePolicy.getPolicyNumber() != null) {
            updateInsurancePolicy.setPolicyNumber(insurancePolicy.getPolicyNumber());
        }
        if (insurancePolicy.getPremium() != null) {
            updateInsurancePolicy.setPremium(insurancePolicy.getPremium());
        }
        if (insurancePolicy.getStartDate() != null) {
            updateInsurancePolicy.setStartDate(insurancePolicy.getStartDate());
        }
        if (insurancePolicy.getEndDate() != null) {
            updateInsurancePolicy.setEndDate(insurancePolicy.getEndDate());
        }
        if (insurancePolicy.getType() != null) {
            updateInsurancePolicy.setType(insurancePolicy.getType());
        }
        if (insurancePolicy.getCoverageAmount() != null) {
            updateInsurancePolicy.setCoverageAmount(insurancePolicy.getCoverageAmount());
        }

        policyService.updatePolicy(updateInsurancePolicy);
        return ResponseEntity.ok(updateInsurancePolicy);
    }

    @DeleteMapping(path = "/Dpolicy/{id}")
    public ResponseEntity<HttpStatus> deletePolicy(@PathVariable long id){

        InsurancePolicy insurancePolicy = policyService.getInsurancePolicyById(id)
                .orElseThrow(() -> new ResourceNotFoundException("policy not exist with id: " + id));

        policyService.delete(insurancePolicy);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
//    @PostMapping(path="/Pclaim")
//    public Claim createClaim(@RequestBody Claim claim) {
//    return policyService.createClaim(claim);
//    }


}
