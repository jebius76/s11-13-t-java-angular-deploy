package com.noCountry13.Iot.controller;

import com.noCountry13.Iot.Model.Entity.DevType;
import com.noCountry13.Iot.Model.Entity.Dto.DevTypeDto;
import com.noCountry13.Iot.Service.Implements.DevTypeServiceImplement;
import com.noCountry13.Iot.security.util.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/devType")
public class DevTypeController {

    @Autowired
    private DevTypeServiceImplement devTypeServiceImplement;

    @PostMapping
    public ResponseEntity<?> newDevType(@RequestBody @Valid DevTypeDto devTypeDto) {
        try {
            return new ResponseEntity<>(devTypeServiceImplement.saveType(devTypeDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDevType(@PathVariable Long id) {
        try {
            if (id != null) {
                devTypeServiceImplement.deleteType(id);
                return new ResponseEntity<>(new Mensaje("Tipo de dispositivo eliminado"), HttpStatus.OK);
            }
            return new ResponseEntity<>(new Mensaje("Error al no recibir parametro valido"), HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje("Error al eliminar el tipo de dispositivo: " +
                    e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> updateDevType(@PathVariable Long id, @RequestBody @Valid DevTypeDto devTypeDto) {
        try {
            if (devTypeServiceImplement.findTypeById(id) != null) {
                return new ResponseEntity(devTypeServiceImplement.updateType(id, devTypeDto), HttpStatus.OK);
            }
            return new ResponseEntity<>(new Mensaje("Tipo de dispositivo no encontrado"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<DevType>> findAllDevTypes() {
        try{
            List<DevType> allTypes = devTypeServiceImplement.listAllTypes();
            return new ResponseEntity<>(allTypes, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findDevTypeById(@PathVariable Long id){
        try {
            DevType typeById = devTypeServiceImplement.findTypeById(id);
            if(typeById != null){
                return new ResponseEntity<>(typeById, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(new Mensaje("Tipo de dispositivo no encontrado"), HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(new Mensaje("Error al obtener el tipo de dispositivo: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
