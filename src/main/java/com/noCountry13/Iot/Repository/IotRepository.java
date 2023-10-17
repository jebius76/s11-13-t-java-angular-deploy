package com.noCountry13.Iot.Repository;

import com.noCountry13.Iot.Model.Entity.Iot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IotRepository extends JpaRepository<Iot, Long> {
}
