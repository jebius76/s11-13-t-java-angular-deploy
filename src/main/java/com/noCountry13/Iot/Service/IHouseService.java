package com.noCountry13.Iot.Service;

import com.noCountry13.Iot.Model.Entity.Device;
import com.noCountry13.Iot.Model.Entity.Dto.HouseDto;
import com.noCountry13.Iot.Model.Entity.House;

import java.util.List;

public interface IHouseService {
    House create(HouseDto houseDto);
    HouseDto update(HouseDto houseDto, Long id);
    void  delete(Long id);
    List<House> allHouse();
    House findById(Long id);
    House addDevice(Device device, Long id);
}
