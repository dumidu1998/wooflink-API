package com.nibm.wooflink.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
public class InvoiceItem {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @CreatedDate
    private Date addedOn;

    @CreationTimestamp
    private Date created;

    @Column(nullable = false, precision = 10,scale = 2)
    private BigDecimal price;

    @ManyToOne
    @JsonIgnore
    private Invoice invoice;
}
