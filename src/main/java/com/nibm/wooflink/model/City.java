package com.nibm.wooflink.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
public class City {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String longitude;

    @Column(nullable = false)
    private String latitude;

    @OneToOne
    @JsonIgnore
    private District district;

    @OneToOne(targetEntity = User.class, mappedBy = "city", cascade = CascadeType.ALL)
    @JsonIgnore
    private User user;
}
