package com.noCountry13.Iot.Controller;

import com.noCountry13.Iot.Model.Entity.Device;
import com.noCountry13.Iot.Model.Entity.Dto.Device.EnvironmentDto;
import com.noCountry13.Iot.Model.Entity.Environment;
import com.noCountry13.Iot.Service.Implements.EnvironmentServiceImplement;
import com.noCountry13.Iot.security.util.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controlador para gestionar las operaciones relacionadas con los ambientes de IoT.
 */

@RestController
@RequestMapping("/api/v1/enviroment")
public class EnvironmentController {

    @Autowired
    private EnvironmentServiceImplement environmentServiceImplement;

    /**
     * Crea un nuevo ambiente.
     *
     * @param newEnvironment El ambiente a crear.
     * @return ResponseEntity con el ambiente creado
     */
    @PostMapping
    public ResponseEntity<?> createEnvironment(@RequestBody @Valid EnvironmentDto newEnvironment) {
        try {
            return new ResponseEntity<>(environmentServiceImplement.save(newEnvironment), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEnvironment(@PathVariable Long id) {
        try {
            if (id != null) {
                environmentServiceImplement.delete(id);
                return new ResponseEntity<>(new Mensaje("Ambiente eliminado"), HttpStatus.OK);
            }
            return new ResponseEntity<>(new Mensaje("Error al no recibir parametro valido"), HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje("Error al eliminar el ambiente: " +
                    e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> updateEnvironment(@PathVariable long id, @RequestBody @Valid Device device) {
        try {
            if (environmentServiceImplement.findEnvironmentById(id) != null) {
                return new ResponseEntity(environmentServiceImplement.updateEnvironment(id, device), HttpStatus.OK);
            }
            return new ResponseEntity<>(new Mensaje("Ambiente no encontrado"), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new Mensaje(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Environment>> findAllEnvironments() {
        try{
            List<Environment> allEnvironments = environmentServiceImplement.findAllEnvironments();
            return new ResponseEntity<>(allEnvironments, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> environmentById(@PathVariable Long id){
        try {
            Environment environmentById = environmentServiceImplement.findEnvironmentById(id);
            if(environmentById != null){
                return new ResponseEntity<>(environmentById, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(new Mensaje("Ambiente no encontrado"), HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(new Mensaje("Error al obtener el ambiente: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/deviceByEnvironment/{id}")
    public ResponseEntity<?> devicesByEnvironmentId(@PathVariable Long id){
        try {
            Environment environmentById = environmentServiceImplement.findEnvironmentById(id);
            if(environmentById != null){
                return new ResponseEntity<>(environmentById.getDevices(), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(new Mensaje("Ambiente no encontrado"), HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(new Mensaje("Error al obtener el ambiente: " + e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
