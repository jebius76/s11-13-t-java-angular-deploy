package com.noCountry13.Iot.Repository;

import com.noCountry13.Iot.Model.Entity.DevType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevTypeRepository extends JpaRepository<DevType, Long> {
}
