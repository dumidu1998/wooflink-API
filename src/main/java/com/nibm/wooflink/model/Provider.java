package com.nibm.wooflink.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
public class Provider {
    @Id
    @GeneratedValue
    private long id;


    @Column(nullable = false)
    private String contactNumber;

    @Column(nullable = false)
    private String shopName;

    @Column(nullable = false)
    private String longitude;

    @Column(nullable = false)
    private String latitude;

    @Column(nullable = false)
    private String about;

    @Column
    private boolean verified;

    @Column
    private boolean currentStatus; // for quick close

    @Column
    @Temporal(TemporalType.TIME)
    @JsonFormat(pattern = "HH:mm")
    private Date openTime;

    @Column
    @Temporal(TemporalType.TIME)
    @JsonFormat(pattern = "HH:mm")
    private Date closeTime;

    @OneToOne
    @JsonIgnore
    private User user;

}
