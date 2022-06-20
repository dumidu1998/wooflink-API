package com.nibm.wooflink.repository;

import com.nibm.wooflink.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
