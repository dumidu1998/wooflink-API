package com.nibm.wooflink.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
public class Customer {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private int petCount =0;

    @Column(nullable = false, precision = 10,scale = 2)
    private BigDecimal walletTotal =BigDecimal.ZERO;

    @OneToOne
    @JsonIgnore
    private User user;

    @OneToMany(targetEntity = Pet.class, mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Pet> pet;
}
