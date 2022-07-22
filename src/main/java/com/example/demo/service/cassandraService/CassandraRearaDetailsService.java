package com.example.demo.service.cassandraService;

import com.example.demo.dto.Detail;
import com.example.demo.dto.ProjectNameId;
import com.example.demo.model.cassandraModel.CassandraReraDetails;
import com.example.demo.model.cassandraModel.City;
import com.example.demo.repository.cassandra_repository.CassandraCityRepository;
import com.example.demo.repository.cassandra_repository.CassandraReraDetailsRepository;
import com.example.demo.repository.cassandra_repository.CassandraReraLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

public interface CassandraRearaDetailsService{
    public List<CassandraReraDetails> findByKeyBuildingId(String buildingId);
    public List<Detail> findDetail(String id);
  //  public Detail findDetail(String city,String locality,String projectName);

   // public List<Detail>findAll();

    Set<String> findCity();

    public List<String > findLocation(String city);
    public Set<ProjectNameId> findProject(String location);
}
@Service
 class ICassandraRearaDetailsService  implements CassandraRearaDetailsService{

    @org.springframework.beans.factory.annotation.Autowired(required=true)
    CassandraReraDetailsRepository cassandraReraDetailsRepository;

    @Autowired
    CassandraCityRepository cassandraCityRepository;
    public List<CassandraReraDetails> findByKeyBuildingId(String id){
        return cassandraReraDetailsRepository.findByKeyBuildingId(id);
    }
  /*  public List<Detail>findAll(){
        try {
            List<CassandraReraDetails> cassandraReraDetails = cassandraReraDetailsRepository.findAll();
            System.out.println(cassandraReraDetails.get(0));
            List<Detail> detailList=new ArrayList<Detail>();
            cassandraReraDetails.forEach(e -> {
                    Detail detail =new Detail(e);
                    detailList.add(detail);

            });
            return  detailList;
        } catch (Exception e) {

            System.out.println("service exception");
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }*/
    public Set<String> findCity(){

        List<City> result = cassandraCityRepository.findAll();

        Set <String> s= new TreeSet<String>();

        for (City c:result){
            s.add(c.getCity());
        }

        return  s;

   /*     Set<String> citySet=new TreeSet<String>();
//        Set<String> citySet=new HashSet<String>();
//        Set<String> citySet=new LinkedHashSet<String>();

        citySet.add("Mumbai");
        citySet.add("Pune");
        citySet.add("Thane");
        citySet.add("Navi Mumbai");
        citySet.add("Bhiwandi-Nizampur");
        citySet.add("Kulgaon-Badlapur");
        citySet.add("Hyderabad");
        citySet.add("Kalyan-Dombivli");

//        System.out.println(citySet);


       return  citySet;*/
    }

    public List<String > findLocation(String city){


      List<City> result= cassandraCityRepository.findLocality(city);


        Set <String> locationSet = result.stream().map(City::getLocation).collect(Collectors.toSet());

        return    locationSet.stream().sorted((o1, o2) -> o1.compareTo(o2)).collect(Collectors.toList());
    }
    public Set<ProjectNameId> findProject(String location){

      List <CassandraReraDetails> result = cassandraReraDetailsRepository.findProject(location);


      Set<ProjectNameId> set = new LinkedHashSet<>();
            for(CassandraReraDetails details : result){
                set.add(new ProjectNameId(details));
            }

        return set;
    }

    public List <Detail> findDetail(String id){
        System.out.println("inside service impl");
        List<CassandraReraDetails> cassandraReraDetails = cassandraReraDetailsRepository.findByUserInput(id);

        Set<String> reraIdList= cassandraReraDetails.stream().map(e->e.getKey().getReraId()).collect(Collectors.toSet());

        List<Detail> details=new ArrayList<>();
        for (String reraId: reraIdList){
            details.add(new Detail(cassandraReraDetails,reraId));

        }





        return  details;
    }
/*    public Detail findDetail(String city,String location,String projectName){
       List< CassandraReraDetails> cassandraReraDetails = cassandraReraDetailsRepository.findDetail(city,location,projectName);

        System.out.println(cassandraReraDetails);

       Detail detail =new Detail();


        return detail;

    }*/
}
