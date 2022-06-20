package com.nibm.wooflink.repository;

import com.nibm.wooflink.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
