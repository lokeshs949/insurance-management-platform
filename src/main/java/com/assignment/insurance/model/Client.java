package com.assignment.insurance.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name="CLIENT")
@Table(name="CLIENT")
public class Client {
    @Id
    @SequenceGenerator(
            name="client_sequence",
            sequenceName = "client_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "client_sequence"
    )
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "date_of_birth")
    private LocalDate dob;
    @Column(name = "address")
    private String address;
    @Column(name = "contact")
    private String contact;
//"    @OneToMany(mappedBy = "client",orphanRemoval = true,cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
//   private List<InsurancePolicy> policies=new ArrayList<>();"
    public Client() {
    }


    public Client(Long id, String name, LocalDate dob, String address, String contact) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.contact = contact;
    }

    public Client(String name, LocalDate dob, String address, String contact) {
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.contact = contact;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

//    public List<InsurancePolicy> getInsurancePolicy() {
//        return insurancePolicy;
//    }
//
//    public void setInsurancePolicy(List<InsurancePolicy> insurancePolicy) {
//        this.insurancePolicy = insurancePolicy;
//    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", address='" + address + '\'' +
                ", phone=" + contact +
                '}';
    }
}
