package com.noCountry13.Iot.Repository;

import com.noCountry13.Iot.Model.Entity.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {
}