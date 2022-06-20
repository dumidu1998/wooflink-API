package com.nibm.wooflink.repository;

import com.nibm.wooflink.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
