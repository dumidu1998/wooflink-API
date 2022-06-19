package com.nibm.wooflink.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nibm.wooflink.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue
    private long id;


    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false,unique = true)
    private String contactNo;

    @Column(nullable = false)
    private String password;

    @Column()
    private String street;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @CreationTimestamp
    private Date addedDate;

    @OneToOne
    @JsonIgnore
    private City city;

    @OneToOne(targetEntity = Customer.class, mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private Customer customer;

}
