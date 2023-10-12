package com.noCountry13.Iot.Service;

import com.noCountry13.Iot.Model.Entity.Device;
import com.noCountry13.Iot.Model.Entity.Dto.Device.DeviceDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define los métodos para gestionar operaciones relacionadas con dispositivos IoT.
 */
@Service
public interface DeviceService {

    /**
     * Guarda un dispositivo en la base de datos.
     *
     * @param devicedto El dispositivo a guardar.
     * @return El dispositivo guardado.
     */
    public Device save(DeviceDto devicedto);

    /**
     * Actualiza un dispositivo en la base de datos
     *
     * @param id, devicedto El dispositivo a editar.
     *
     */
    public Device updateDevice(Long id, DeviceDto devicedto);
    /**
     * Elimina un dispositivo por su ID.
     *
     * @param id_device El ID del dispositivo a eliminar.
     */
    public void delete(Long id_device);

    /**
     * Busca un dispositivo por su ID.
     *
     * @param id_device El ID del dispositivo a buscar.
     * @return El dispositivo encontrado, o null si no se encuentra.
     */
    public Optional findById(Long id_device);

    /**
     * Obtiene una lista de todos los dispositivos disponibles.
     *
     * @return Una lista de dispositivos.
     */
    public List<Device> findAll();
}
