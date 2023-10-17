package com.noCountry13.Iot.Service;

import com.noCountry13.Iot.Model.Entity.Iot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IoTService {

   // public Page<Iot> listAllItems(Pageable pageable);


    public List<Iot> listAllItems();
}