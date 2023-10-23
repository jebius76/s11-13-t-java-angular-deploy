package com.noCountry13.Iot.controller;
import com.noCountry13.Iot.Model.Entity.Environment;
import com.noCountry13.Iot.Service.Implements.HouseServiceImpl;
import com.noCountry13.Iot.security.util.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.noCountry13.Iot.Model.Entity.House;
import com.noCountry13.Iot.Model.Entity.Dto.HouseDto;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/house")
public class HouseController {
    @Autowired
    private HouseServiceImpl  houseService;

    // Creando una nueva casa
    @PostMapping("/create")
    public ResponseEntity<?> newHouse(@Valid @RequestBody HouseDto newHouse) {
        House house = houseService.create(newHouse);
        return new ResponseEntity<>(house, HttpStatus.CREATED);
    }

    // Devolviendo
    @GetMapping("/getAll")
    public ResponseEntity<List<HouseDto>> getAllHouses() {
        return new ResponseEntity(houseService.allHouse(),HttpStatus.OK) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getHouseById(@PathVariable Long id) {
        return new ResponseEntity<>(houseService.findById(id),HttpStatus.OK);
    }

    // Actualizando por ID
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateHouse( @Valid @PathVariable Long id, @RequestBody HouseDto updatedHouse) {
        HouseDto houseDto = houseService.update(updatedHouse,id);
        return new ResponseEntity<>(houseDto,HttpStatus.OK) ;
    }




    // Eliminando por ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteHouse(@PathVariable Long id) {
        houseService.delete(id);
        return new ResponseEntity<>( "eliminado", HttpStatus.OK);
    }
}
