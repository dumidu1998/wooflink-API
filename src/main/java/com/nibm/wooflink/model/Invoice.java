package com.nibm.wooflink.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nibm.wooflink.enums.InvoiceStatus;
import com.nibm.wooflink.enums.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
public class Invoice {

    @Id
    @GeneratedValue
    private long id;

    @CreatedDate
    private Date openedOn;

    @Column
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-dd-MM")
    private Date issueDate;

    @Enumerated(EnumType.STRING)
    private InvoiceStatus invoiceStatus;

    @Column(nullable = false, precision = 10,scale = 2)
    private BigDecimal total =BigDecimal.ZERO;

    @Column(nullable = false, precision = 10,scale = 2)
    private BigDecimal discount =BigDecimal.ZERO;

    @Column
    private Float couponDiscount;

    @OneToOne
    @JsonIgnore
    private Invoice invoice;

    @OneToMany(targetEntity = InvoiceItem.class, mappedBy = "invoice", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<InvoiceItem> invoiceItems;

}
