package com.example.demo.model.cassandraModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("rera_details_city")
public class City {
    @PrimaryKey
    @Column(value = "location")
    private String location;
    @Column(value = "city")
    private String city;
}
