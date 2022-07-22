package com.example.demo.controller.cassandraController;

import com.example.demo.dto.Detail;
import com.example.demo.dto.Input;
import com.example.demo.dto.ProjectNameId;
import com.example.demo.dto.UserInput;
import com.example.demo.model.cassandraModel.CassandraReraDetails;
import com.example.demo.model.cassandraModel.CassandraReraLocation;
import com.example.demo.service.cassandraService.CassandraRearaDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/rera")
public class CassandraReraController {
    @Autowired
    CassandraRearaDetailsService cassandraRearaDetailsService;

    @GetMapping(value = "/detail/{building_id}")
    public List<CassandraReraDetails> findByKeyBuildingId(@PathVariable String buildingId){
        System.out.println("de ki");
        System.out.println(buildingId);
        return cassandraRearaDetailsService.findByKeyBuildingId(buildingId);
    }

/*    @GetMapping(value = "/all")
    public List<Detail>findAll(){
        try{
            System.out.println("all me  hooo...");
            return cassandraRearaDetailsService.findAll();
        }catch (Exception e){
            System.out.println("all exception");
            return null;
        }
    }*/

    @GetMapping(value = "/city")
        public Set<String> findCity(){
        System.out.println("city controller");
            return cassandraRearaDetailsService.findCity();
        }

        @PostMapping(value = "/find_location")
    public List<String> findLocation(@RequestBody String city){

            System.out.println("mi aahe controller"+ city);

       return cassandraRearaDetailsService.findLocation(city);

        }
        @PostMapping(value = "/find_project")
    public Set<ProjectNameId>findProject(@RequestBody String location){

        return cassandraRearaDetailsService.findProject(location);
        }

    @PostMapping(value = "/detail")
    public  List<Detail> findDetail(@RequestBody String buildingId){
        try{
            System.out.println(buildingId);

            return cassandraRearaDetailsService.findDetail(buildingId);

        }catch (Exception e){
            System.out.println("inside exception");
            e.printStackTrace();

            return new ArrayList<>();
        }
    }

/*    @PostMapping(value = "/find_detail")
    public  Detail findDetail(@RequestBody Input input){
        try{
            String city=input.getCity();
            String locality=input.getLocation();
            String buildingId= input.getBuildingId();
            System.out.println(city);
            System.out.println(locality);
            System.out.println(buildingId);

            return cassandraRearaDetailsService.findDetail(city,locality,buildingId);

        }catch (Exception e){
            System.out.println("inside exception");
            e.printStackTrace();

            return new  Detail ();
        }
    }*/
}
