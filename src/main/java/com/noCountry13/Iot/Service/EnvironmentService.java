package com.noCountry13.Iot.Service;

import com.noCountry13.Iot.Model.Entity.Device;
import com.noCountry13.Iot.Model.Entity.Dto.Device.EnvironmentDto;
import com.noCountry13.Iot.Model.Entity.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Interfaz que define los métodos para gestionar operaciones relacionadas con los ambientes de IoT.
 */
@Service
public interface EnvironmentService {
    /**
     * Guarda un ambiente en la base de datos.
     *
     * @param environmentDto El ambiente a guardar.
     * @return El ambiente guardado.
     */
    public Environment save(EnvironmentDto environmentDto);
    /**
     * Actualiza los dispositivos del ambiente en la base de datos
     *
     * @param id, device id y dispositivo para agregar.
     *
     */
    public Environment updateEnvironment(Long id, Device device);
    /**
     * Elimina un ambiente por su ID.
     *
     * @param id El ID del ambiente a eliminar.
     */
    public void delete(Long id);
    /**
     * Busca un ambiente por su ID.
     *
     * @param id El ID del ambiente a buscar.
     * @return El ambiente encontrado, o null si no se encuentra.
     */
    public Environment findEnvironmentById(Long id);
    /**
     *Obtiene la lista de todos los ambientes que se han generado
     */
    public List<Environment> findAllEnvironments();
}
