package com.nibm.wooflink.repository;

import com.nibm.wooflink.model.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {
}
