package com.example.demo.repository.cassandra_repository;

import com.example.demo.model.cassandraModel.City;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CassandraCityRepository extends CassandraRepository<City,String> {

    @Query(value = "select * from rera_details_city where city=:city ALLOW FILTERING")
    List<City> findLocality(String city);
}