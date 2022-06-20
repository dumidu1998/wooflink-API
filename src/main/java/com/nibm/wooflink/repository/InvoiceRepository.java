package com.nibm.wooflink.repository;

import com.nibm.wooflink.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
