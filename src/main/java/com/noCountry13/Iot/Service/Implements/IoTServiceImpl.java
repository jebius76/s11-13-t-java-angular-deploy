package com.noCountry13.Iot.Service.Implements;

import com.noCountry13.Iot.Model.Entity.Iot;
import com.noCountry13.Iot.Repository.IotRepository;
import com.noCountry13.Iot.Service.IoTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IoTServiceImpl implements IoTService {
    @Autowired
    private IotRepository iotRepository;


    @Override
    @Transactional(readOnly = true)
    public List<Iot> listAllItems() {
        return iotRepository.findAll();
    }
}
