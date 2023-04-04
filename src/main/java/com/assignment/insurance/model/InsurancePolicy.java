package com.assignment.insurance.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name="policy")
@Table(name="policy")
public class InsurancePolicy {

    @Id
    @SequenceGenerator(
            name="policy_sequence",
            sequenceName = "policy_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "policy_sequence"
    )
    private Long id;
    @Column(name = "policyNumber")
    private String policyNumber;
    @Column(name = "type")
    private String type;
    @Column(name = "coverageAmount")
    private BigDecimal coverageAmount;
    @Column(name = "premium")
    private BigDecimal premium;
    @Column(name = "startDate")
    private LocalDate startDate;
    @Column(name = "endDate")
    private LocalDate endDate;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="client_id",nullable=false,referencedColumnName = "id",foreignKey = @ForeignKey(name = "insurance_policy_fk"))
    private Client client;


//    @OneToMany(mappedBy = "insurancePolicy",orphanRemoval = true,cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
//    private List<Claim> claims=new ArrayList<>();
    @Column(
            insertable=false, updatable=false
    )
    private Long client_id;

    public InsurancePolicy() {
    }

    public InsurancePolicy(String policyNumber, String type, BigDecimal coverageAmount, BigDecimal premium, LocalDate startDate, LocalDate endDate, Client client) {
        this.policyNumber = policyNumber;
        this.type = type;
        this.coverageAmount = coverageAmount;
        this.premium = premium;
        this.startDate = startDate;
        this.endDate = endDate;
        this.client = client;
    }

    public InsurancePolicy(Long id, String policyNumber, String type, BigDecimal coverageAmount, BigDecimal premium, LocalDate startDate, LocalDate endDate, Client client, Long client_id) {
        this.id = id;
        this.policyNumber = policyNumber;
        this.type = type;
        this.coverageAmount = coverageAmount;
        this.premium = premium;
        this.startDate = startDate;
        this.endDate = endDate;
        this.client = client;
        this.client_id = client_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getCoverageAmount() {
        return coverageAmount;
    }

    public void setCoverageAmount(BigDecimal coverageAmount) {
        this.coverageAmount = coverageAmount;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

//    public void addClaim(Claim claim) {
//        if (!this.claims.contains(claim)) {
//            this.claims.add(claim);
//            claim.setInsurancePolicy(this);
//        }
//    }
//
//    public void removeClaim(Claim claim) {
//        if (this.claims.contains(claim)) {
//            this.claims.remove(claim);
//            claim.setInsurancePolicy(null);
//        }
//    }

    @Override
    public String toString() {
        return "InsurancePolicy{" +
                "id=" + id +
                ", policyNumber='" + policyNumber + '\'' +
                ", type='" + type + '\'' +
                ", coverageAmount=" + coverageAmount +
                ", premium=" + premium +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", client=" + client +
                '}';
    }

}
