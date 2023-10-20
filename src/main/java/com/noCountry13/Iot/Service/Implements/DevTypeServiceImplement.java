package com.noCountry13.Iot.Service.Implements;

import com.noCountry13.Iot.Model.Entity.DevType;
import com.noCountry13.Iot.Model.Entity.Dto.DevTypeDto;
import com.noCountry13.Iot.Repository.DevTypeRepository;
import com.noCountry13.Iot.Service.DevTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DevTypeServiceImplement implements DevTypeService {
    @Autowired
    private DevTypeRepository devTypeRepository;

    @Override
    public DevType saveType(DevTypeDto devTypeDto) {
        DevType newType = new DevType(
                devTypeDto.getName(),
                devTypeDto.getDescription()
        );
        devTypeRepository.save(newType);
        return newType;
    }

    @Override
    @Transactional
    public DevType updateType(Long id, DevTypeDto devTypeDto) {
        DevType type = devTypeRepository.findById(id).orElseThrow(() -> new IllegalStateException("Tipo de dispositivo no encontrado con el ID: " + id));

        type.setName(devTypeDto.getName());
        type.setDescription(devTypeDto.getDescription());
        devTypeRepository.save(type);

        return type;
    }

    @Override
    public void deleteType(Long id) {
        if (!devTypeRepository.existsById(id)) {
            throw new IllegalStateException("El tipo de dispositivo no existe con el ID: " + id);
        }
        devTypeRepository.deleteById(id);
    }

    @Override
    public List<DevType> listAllTypes() {
        return devTypeRepository.findAll();
    }

    @Override
    public DevType findTypeById(Long id) {
        DevType type = devTypeRepository.findById(id).orElseThrow(() -> new IllegalStateException("No se encontró el tipo de dispositivo con ID " + id));
        return type;
    }
}
