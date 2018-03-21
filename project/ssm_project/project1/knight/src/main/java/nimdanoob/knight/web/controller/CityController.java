package nimdanoob.knight.web.controller;

import lombok.experimental.var;
import nimdanoob.knight.web.domain.mapper.CityMapper;
import nimdanoob.knight.web.domain.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("citys")
public class CityController {
    @Autowired
    CityMapper cityMapper;

    @RequestMapping("/test")
    public String test() {
        City record = new City();
        record.setId(0);
        record.setCityName("上海");
        record.setDescription("上海");
        record.setProvinceId(1);
        int insert = cityMapper.insert(record);
        return insert+"";
    }
}
