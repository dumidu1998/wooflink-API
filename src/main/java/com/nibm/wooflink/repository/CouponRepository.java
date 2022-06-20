package com.nibm.wooflink.repository;

import com.nibm.wooflink.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
