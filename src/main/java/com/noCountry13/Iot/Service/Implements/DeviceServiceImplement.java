package com.noCountry13.Iot.Service.Implements;

import com.noCountry13.Iot.Model.Entity.Device;
import com.noCountry13.Iot.Model.Entity.Dto.Device.DeviceDto;
import com.noCountry13.Iot.Repository.DeviceRepository;
import com.noCountry13.Iot.Service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de la interfaz {@link DeviceService} para gestionar operaciones relacionadas con dispositivos IoT.
 */
@Service
public class DeviceServiceImplement implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    /**
     * Guarda un dispositivo en la base de datos.
     *
     * @param devicedTO El dispositivo a guardar.
     * @return El dispositivo guardado.
     */
    @Override
    @Transactional(readOnly=false)
    public Device save(DeviceDto devicedTO) {
        String type = String.valueOf(devicedTO.getType());
        Device newDevice = new Device();
        newDevice.setBrand(devicedTO.getBrand());
        newDevice.setSn(devicedTO.getSn());
        newDevice.setType(devicedTO.getType());
        newDevice.setDescription(devicedTO.getDescription());
        newDevice.setSubtopic(devicedTO.getSubtopic());
        newDevice.setMqttClient(devicedTO.getSubtopic());
        newDevice.setSubtopic(devicedTO.getMqttClient());
        newDevice.setUnit(devicedTO.getUnit());

        if(type == null){
            throw new IllegalStateException("Tipo invalido");
        }
        deviceRepository.save(newDevice);
        return newDevice;
    }

    /**
     *
     * Actualiza el dispositivo
     * @param devicedto y id del dispositivo
     * */

    @Override
    @Transactional
    public Device updateDevice(Long id, DeviceDto devicedto) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("No se encontró el dispositivo con ID " + id));

        device.setBrand(devicedto.getBrand());
        device.setType(devicedto.getType());
        device.setDescription(devicedto.getDescription());
        device.setUnit(devicedto.getUnit());

        deviceRepository.save(device);

        return device;
    }

    /**
     * Elimina un dispositivo por su ID.
     *
     * @param id_device El ID del dispositivo a eliminar.
     */
    @Override
    @Transactional(readOnly=false)
    public void delete(Long id_device) {
        if(!deviceRepository.existsById(id_device)){
            throw new IllegalStateException("El dispositivo no existe");
        }
        deviceRepository.deleteById(id_device);
    }

    /**
     * Busca un dispositivo por su ID.
     *
     * @param id_device El ID del dispositivo a buscar.
     * @return El dispositivo encontrado, o null si no se encuentra.
     */
    @Override
    @Transactional(readOnly=true)
    public Optional<Device> findById(Long id_device) {
        return deviceRepository.findById(id_device);
    }

    /**
     * Obtiene una lista de todos los dispositivos disponibles.
     *
     * @return Una lista de dispositivos.
     */
    @Override
    @Transactional(readOnly=false)
    public List<Device> findAll() {
        return deviceRepository.findAll();
    }
}
