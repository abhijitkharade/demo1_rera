package com.example.demo.controller.cassandraController;

import com.example.demo.dto.Input;
import com.example.demo.dto.LocationDetail;
import com.example.demo.model.cassandraModel.CassandraReraLocation;
import com.example.demo.model.cassandraModel.CassandraReraLocationKey;
import com.example.demo.service.cassandraService.CassandraRearaDetailsService;
import com.example.demo.service.cassandraService.CassandraReraLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/location")
public class CassandraLocationController {

@Autowired
    CassandraReraLocationService cassandraReraLocationService;


    @PostMapping(value = "/detail")
    public LocationDetail findDetail(@RequestBody Input input) {
        System.out.println(input);
        try {
            return cassandraReraLocationService.findDetail(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping(value = "/id")
    public Optional findDetail(@RequestBody CassandraReraLocationKey key){
        try{
            return cassandraReraLocationService.findById(key);
        }catch(Exception e){
            System.out.println("inside controller exception");
            throw new RuntimeException();
        }

    }
    @GetMapping(value = "/find_city")
    public Set<String >findCity(){

        return  cassandraReraLocationService.findCity();
    }
    @PostMapping(value = "/find_area_under_city")
            public Set<String> findArea(@RequestBody String city){
        System.out.println(city +"controller");

        return cassandraReraLocationService.findArea(city);
    }
/*    @PostMapping(value = "/find_project_under_area")
        public Map<String ,Set<String> > findProject(@RequestBody String area){
            return cassandraReraLocationService.findProject(area);
        }
        */
        @PostMapping(value = "/find_project_under_area")
        public Map<String ,String > findProject(@RequestBody String area){
            return cassandraReraLocationService.findProject(area);
    }
}