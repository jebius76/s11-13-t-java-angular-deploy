package com.noCountry13.Iot.Service;

import com.noCountry13.Iot.Model.Entity.DevType;
import com.noCountry13.Iot.Model.Entity.Dto.DevTypeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DevTypeService {
    public DevType saveType(DevTypeDto devTypeDto);
    public DevType updateType(Long id, DevTypeDto devTypeDto);
    public void deleteType(Long id);
    public List<DevType> listAllTypes();
    public DevType findTypeById(Long id);
}
