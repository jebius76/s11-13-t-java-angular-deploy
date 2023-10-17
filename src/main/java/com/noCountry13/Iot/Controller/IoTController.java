package com.noCountry13.Iot.Controller;

import com.noCountry13.Iot.Model.Entity.Iot;
import com.noCountry13.Iot.Service.IoTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/iot")
public class IoTController {

    @Autowired
    private IoTService ioTService;

    @GetMapping("/listAll")
    public List<Iot> listAllItems() {
        return ioTService.listAllItems();
    }
}
