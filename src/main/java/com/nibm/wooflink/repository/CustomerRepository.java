package com.nibm.wooflink.repository;

import com.nibm.wooflink.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
