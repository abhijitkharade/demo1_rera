package com.example.demo.service.cassandraService;

import com.example.demo.dto.Input;
import com.example.demo.dto.LocationDetail;
import com.example.demo.model.cassandraModel.CassandraReraLocation;
import com.example.demo.model.cassandraModel.CassandraReraLocationKey;
import com.example.demo.repository.cassandra_repository.CassandraReraLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CassandraReraLocationService {
    @Autowired
    CassandraReraLocationRepository cassandraReraLocationRepository;
   public LocationDetail findDetail(Input input){
        String city =input.getCity();
        String location=input.getLocation();
        String buildingId=input.getBuildingId();
        System.out.println(city);
        System.out.println(location);
        System.out.println(buildingId);

       List<CassandraReraLocation> detail= cassandraReraLocationRepository.findDetail(city,location,buildingId);

       LocationDetail result =new LocationDetail(detail.get(0));
        Integer noOfApartment =0;
        Integer noOfBookedApartment=0;
        Double percentageOfBooking=0.0;

          for (int i=0;i< detail.size();i++){
          noOfApartment = noOfApartment + detail.get(i).getNoOfApartment();
          noOfBookedApartment = noOfBookedApartment + detail.get(i).getNoOfBookedApartment();
          }
        percentageOfBooking=(noOfBookedApartment *100.00 / noOfApartment);
          result.setPercentageOfBooking(percentageOfBooking);
        result.setNoOfApartment(noOfApartment);
        result.setNoOfBookedApartment(noOfBookedApartment);

        return result;
    }
    public Optional findById(CassandraReraLocationKey key) {

               Optional detail = cassandraReraLocationRepository.findById(key);

         return detail;
    }
    public Set<String> findArea(String city){
            System.out.println(city+"service");


      return cassandraReraLocationRepository.findByCity(city).stream().map(CassandraReraLocation::getLocation).collect(Collectors.toSet());

    }
/*    public Map<String,Set<String>> findProject(String area){
//       Set<Map> set =new HashSet<Map>();

        List < CassandraReraLocation> detail= cassandraReraLocationRepository.findByArea(area);
        Map<String,Set<String > > map= new HashMap<String,Set<String>>();
//        System.out.println(detail);

        for(CassandraReraLocation e : detail){
            if(map.containsKey(e.getKey().getBuildingId())) {
               map.get(e.getKey().getBuildingId()).add(e.getKey().getProjectName());
            }
            else{
                Set<String > newSet= new HashSet<>();
                newSet.add(e.getKey().getProjectName());
                map.put(e.getKey().getBuildingId(),newSet);
            }
//      System.out.println(e.getKey().getBuildingId()  +"     "+ e.getKey().getProjectName());
//            System.out.println(map);
        }
        return map;
    }*/
public Map<String,String> findProject(String area){

    //{ buildingId : projectName    }

    List < CassandraReraLocation> detail= cassandraReraLocationRepository.findByArea(area);

    Map<String,String > map= new HashMap<String,String>();

    for(CassandraReraLocation e : detail){
            map.put(e.getKey().getProjectName(),e.getKey().getBuildingId());

    }
    return map;
}
    public  Set<String> findCity(){



       return cassandraReraLocationRepository.findCity().stream().map(CassandraReraLocation::getCity).collect(Collectors.toSet());
    }
}