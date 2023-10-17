package com.noCountry13.Iot.Service.Implements;

import com.noCountry13.Iot.Model.Entity.Device;
import com.noCountry13.Iot.Model.Entity.Dto.Device.EnvironmentDto;
import com.noCountry13.Iot.Model.Entity.Environment;
import com.noCountry13.Iot.Repository.EnvironmentRepository;
import com.noCountry13.Iot.Service.EnvironmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Implementación de la interfaz {@link EnvironmentService} para gestionar operaciones relacionadas
 * con ambientes de IoT.
 */
@Service
public class EnvironmentServiceImplement implements EnvironmentService {
    @Autowired
    private EnvironmentRepository environmentRepository;

    @Override
    public Environment save(EnvironmentDto environmentDto) {
        Environment newEnvironment = new Environment(
                        environmentDto.getName(),
                        environmentDto.getDescription(),
                        environmentDto.getSubTopic()
        );
        environmentRepository.save(newEnvironment);
        return newEnvironment;
    }

    @Override
    @Transactional
    public Environment updateEnvironment(Long id, Device device) {
        Environment environmentById = environmentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Ambientae no encontrado con el ID: " + id));
        environmentById.getDevices().add(device);

        environmentRepository.save(environmentById);
        return environmentById;
    }
    @Override
    public void delete(Long id) {
        Environment environmentById = environmentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Ambientae no encontrado con el ID: " + id));
        if(environmentById != null){
            environmentRepository.deleteById(id);
        }
    }

    @Override
    public Environment findEnvironmentById(Long id) {
        Environment environment = environmentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("No se encontró el dispositivo con ID " + id));
        return environment;
    }

    @Override
    public List<Environment> findAllEnvironments() {
        return environmentRepository.findAll();
    }

}
