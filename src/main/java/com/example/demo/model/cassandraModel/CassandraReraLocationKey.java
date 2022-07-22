package com.example.demo.model.cassandraModel;

import com.example.demo.util.TableConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;

@PrimaryKeyClass
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CassandraReraLocationKey   {

    @PrimaryKeyColumn(name = TableConstants.COLUMN_BUILDING_ID, ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String buildingId;

    @PrimaryKeyColumn(name = TableConstants.COLUMN_RECORD_ID, ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private String recordId;


    @PrimaryKeyColumn(name = TableConstants.COLUMN_PROJECT_NAME, ordinal = 2, type = PrimaryKeyType.PARTITIONED)
    private String projectName;

    @PrimaryKeyColumn(name = TableConstants.COLUMN_APARTMENT_ID, ordinal = 3, type = PrimaryKeyType.PARTITIONED)
    private String apartmentId;



}
