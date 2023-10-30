package com.noCountry13.Iot.Service.Implements;

import com.noCountry13.Iot.Model.Entity.Device;
import com.noCountry13.Iot.Model.Entity.Dto.HouseDto;
import com.noCountry13.Iot.Model.Entity.Dto.EnvironmentDto;
import com.noCountry13.Iot.Model.Entity.House;
import com.noCountry13.Iot.Model.Entity.Environment;
import com.noCountry13.Iot.Repository.HouseRepository;
import com.noCountry13.Iot.Service.IHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList;
@Service
public class HouseServiceImpl implements IHouseService {

    @Autowired
    HouseRepository houseRepository;
    @Override
    public House create(HouseDto houseDto) {
        House house = new House();
        house.setAddress(houseDto.getAddress());
        house.setDescription(houseDto.getDescription());
        house.setSubtopic(houseDto.getSubtopic());
        return houseRepository.save(house);
    }
    @Override
    public HouseDto update(HouseDto updateHouse, Long id) {
        Optional<House> existingHouse = houseRepository.findById(id);
        if (existingHouse.isPresent()) {
            House house = existingHouse.get();
            house.setAddress(updateHouse.getAddress());
            house.setDescription(updateHouse.getDescription());
            house.setSubtopic(updateHouse.getSubtopic());
            houseRepository.save(house);
        }else{
            throw new RuntimeException("House not found with id: " + id);
        }
        return updateHouse;
    }

    @Override
    public void delete(Long id) {
        House house = houseRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Casa no encontrada con el ID: " + id));;
        houseRepository.deleteById(id);
    }

    @Override
    public List<House> allHouse() {
        return new ArrayList<>(houseRepository.findAll());
    }

    @Override
    public House findById(Long id) {
        return houseRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("No se encontró la casa con ID " + id));
    }

    @Override
    public House addDevice(Device device, Long id) {
        House houseById = houseRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("No se encontró la casa con ID " + id));;
        houseById.getDevices().add(device);
        houseRepository.save(houseById);
        return houseById;
    }

}
