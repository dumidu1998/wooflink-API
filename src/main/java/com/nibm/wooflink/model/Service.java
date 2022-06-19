package com.nibm.wooflink.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nibm.wooflink.enums.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
public class Service {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String contactNumber;

    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    @Column
    @Temporal(TemporalType.TIME)
    @JsonFormat(pattern = "HH:mm")
    private Date startTime;

    @Column
    @Temporal(TemporalType.TIME)
    @JsonFormat(pattern = "HH:mm")
    private Date endTime;

    @Column
    private boolean openAllDay;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @ManyToOne
    @JsonIgnore
    private Provider provider;

    @OneToMany(targetEntity = Booking.class, mappedBy = "service", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Booking> bookings;

}
