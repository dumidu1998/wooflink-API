package com.nibm.wooflink.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Booking {
    @Id
    @GeneratedValue
    private long id;

    @CreationTimestamp
    private Date addedOn;

    @Column
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-dd-MM")
    private Date date;

    @Column
    @Temporal(TemporalType.TIME)
    @JsonFormat(pattern = "HH:mm")
    private Date time;

    @Column(nullable = false)
    private String note;

    @ManyToOne
    @JsonIgnore
    private Service service;

    @OneToOne(targetEntity = Invoice.class, mappedBy = "invoice", cascade = CascadeType.ALL)
    @JsonIgnore
    private Invoice invoice;

}
