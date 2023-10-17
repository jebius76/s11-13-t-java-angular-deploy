package com.noCountry13.Iot.Repository;

import com.noCountry13.Iot.Model.Entity.Environment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnvironmentRepository extends JpaRepository<Environment, Long> {
}
