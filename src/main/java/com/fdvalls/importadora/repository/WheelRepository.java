package com.fdvalls.importadora.repository;

import java.util.List;

import com.fdvalls.importadora.model.Wheel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WheelRepository extends JpaRepository<Wheel, Long> {

    Wheel findWheelById(Long id);

    List<Wheel> findAll();


}
