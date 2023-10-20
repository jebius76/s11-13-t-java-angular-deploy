package com.noCountry13.Iot.controller;

import com.noCountry13.Iot.Model.Entity.Device;
import com.noCountry13.Iot.Model.Entity.Dto.DeviceDto;
import com.noCountry13.Iot.Service.Implements.DeviceServiceImplement;
import com.noCountry13.Iot.security.util.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


/**
 * Controlador para gestionar las operaciones relacionadas con los dispositivos IoT.
 */
@RestController
@RequestMapping("/api/v1/device")
public class DeviceController {

    @Autowired
    private DeviceServiceImplement deviceServiceImplement;

    /**
     * Crea un nuevo dispositivo.
     *
     * @param newDevice El dispositivo a crear.
     * @return ResponseEntity con el dispositivo recién creado si tiene éxito.
     */
    @PostMapping
    public ResponseEntity<?> newDevice(@RequestBody DeviceDto newDevice){

        try{
            if (newDevice.getBrand() == null || newDevice.getBrand().isEmpty()) {
                return new ResponseEntity<>(new Mensaje("Brand no puede estar vacia"), HttpStatus.BAD_REQUEST);
            }
            if (newDevice.getSn() == null || newDevice.getSn().isEmpty()) {
                return new ResponseEntity<>(new Mensaje("SN no puede estar vacia"), HttpStatus.BAD_REQUEST);
            }
            if (newDevice.getDescription() == null || newDevice.getDescription().isEmpty()) {
                return new ResponseEntity<>(new Mensaje("Description no puede estar vacia"), HttpStatus.BAD_REQUEST);
            }
            if (newDevice.getType() == null) {
                return new ResponseEntity<>(new Mensaje("Type no puede estar vacia"), HttpStatus.BAD_REQUEST);
            }
            if (newDevice.getMqttClient() == null || newDevice.getMqttClient().isEmpty()) {
                return new ResponseEntity<>(new Mensaje("MqttClient no puede estar vacia"), HttpStatus.BAD_REQUEST);
            }
            if (newDevice.getSubtopic() == null || newDevice.getSubtopic().isEmpty()) {
                return new ResponseEntity<>(new Mensaje("SubTopic no puede estar vacia"), HttpStatus.BAD_REQUEST);
            }
            if (newDevice.getUnit() == null || newDevice.getUnit().isEmpty()) {
                return new ResponseEntity<>(new Mensaje("Unit no puede estar vacia"), HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>( deviceServiceImplement.save(newDevice), HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Elimina un dispositivo por su ID.
     *
     * @param id El ID del dispositivo a eliminar.
     * @return ResponseEntity con el dispositivo eliminado si tiene éxito, o un error si no se encuentra.
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Mensaje> deleteDevice(@PathVariable Long id) {
        try {
            deviceServiceImplement.delete(id);
            return new ResponseEntity<>(new Mensaje("Dispositivo eliminado"), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje("Error al eliminar el dispositivo: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Actualiza un dispositivo existente.
     *
     * @param id y deviceDto El dispositivo con los datos actualizados.
     * @return ResponseEntity con el dispositivo actualizado si tiene éxito, o un error si no se encuentra.
     */
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateDevice(@PathVariable Long id, @RequestBody DeviceDto deviceDto){
        try {
            if (id == null) {
                return new ResponseEntity(new Mensaje("Id de dispositivo no existe"), HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                if (deviceServiceImplement.findById(id) != null) {
                    return new ResponseEntity(deviceServiceImplement.updateDevice(id, deviceDto), HttpStatus.OK);
                }
                return new ResponseEntity<>(new Mensaje("Dispositivo no encontrado"), HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
                return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtiene una lista de todos los dispositivos disponibles.
     *
     * @return ResponseEntity con una lista de objetos DeviceDto que representan los dispositivos.
     */
    @GetMapping("/list")
    public ResponseEntity<List<Device>> deviceList() {
        try {
            List<Device> devices = deviceServiceImplement.findAll();
            return new ResponseEntity<>(devices, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Obtiene un dispositivo por su ID.
     *
     * @param id El ID del dispositivo a obtener.
     * @return ResponseEntity con el dispositivo si se encuentra, o un error si no se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> deviceById(@PathVariable Long id) {
        try {
            Optional<Device> deviceOptional = deviceServiceImplement.findById(id);

            if (deviceOptional.isPresent()) {
                Device device = deviceOptional.get();
                return new ResponseEntity<>(device, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new Mensaje("Dispositivo no encontrado"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje("Error al obtener el dispositivo: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
