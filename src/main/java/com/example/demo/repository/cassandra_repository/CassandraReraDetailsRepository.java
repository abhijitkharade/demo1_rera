package com.example.demo.repository.cassandra_repository;

import com.example.demo.dto.Detail;
import com.example.demo.model.cassandraModel.CassandraReraDetails;
import com.example.demo.model.cassandraModel.CassandraReraLocation;
import com.example.demo.model.cassandraModel.City;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CassandraReraDetailsRepository extends CassandraRepository<CassandraReraDetails,String> {
    List<CassandraReraDetails> findByKeyBuildingId(String buildingId);

    @Query(value = "Select * from rera_details_yukta where building_id =:id ALLOW FILTERING")
    List<CassandraReraDetails> findByUserInput(String id);

    @Query(value = "select * from rera_details_yukta where city=:city and locality=:locality and project_name=:projectName limit 1 ALLOW FILTERING")
    List <CassandraReraDetails> findDetail(String city,String location,String projectName);
    @Query(value = "select * from rera_details_yukta")
    List <CassandraReraDetails> findCity();

    @Query(value = "select * from rera_details_yukta where location =:location ALLOW FILTERING")
    List <CassandraReraDetails> findProject(String location);


}
