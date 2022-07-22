package com.example.demo.repository.cassandra_repository;

import com.example.demo.model.cassandraModel.CassandraReraLocation;
import com.example.demo.model.cassandraModel.CassandraReraLocationKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CassandraReraLocationRepository extends CassandraRepository<CassandraReraLocation, CassandraReraLocationKey> {

        @Query(  "select * from rera_locations where city=:city and location=:location and building_id=:buildingId  ALLOW FILTERING;")
        public  List<CassandraReraLocation> findDetail( String city,String location,String buildingId);

        @Query("Select * from rera_Locations where city=:city ALLOW FILTERING")
        public  List<CassandraReraLocation> findByCity(String city);

        @Query("Select * from rera_Locations where location=:area ALLOW FILTERING")
        public  List<CassandraReraLocation> findByArea(String area);

        @Query("Select * from rera_Locations")
        public  List<CassandraReraLocation> findCity();



}
