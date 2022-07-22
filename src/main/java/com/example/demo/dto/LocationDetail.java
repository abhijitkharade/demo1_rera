package com.example.demo.dto;

import com.example.demo.model.cassandraModel.CassandraReraLocation;
import com.example.demo.model.cassandraModel.CassandraReraLocationKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationDetail {
    private String id;
    private String project_name;
    private String developmentStatus;
    private Double developmentStatusInPercentage;
    private Float rate;
    private Float percentageChangeSinceLaunch;
    private LocalDate proposedDateOfCompletion;
    private Integer noOfApartment;
    private Integer noOfBookedApartment;
    private Double percentageOfBooking;


    public LocationDetail(CassandraReraLocation detail){
        this.id=detail.getKey().getRecordId();
        this.project_name=detail.getKey().getProjectName();
        this.developmentStatus=detail.getDevelopmentStatus();
        this.developmentStatusInPercentage=detail.getDevelopmentStatusInPercentage();
        this.rate=detail.getRate();
//        this.percentageChangeSinceLaunch=detail.GetPercentageChangeSinceLaunch();
        this.proposedDateOfCompletion=detail.getProposedDateOfCompletion();
        this.noOfApartment= detail.getNoOfApartment();
        this.noOfBookedApartment= detail.getNoOfBookedApartment();
        this.percentageOfBooking= detail.getPercentageOfBooking();
    }
}
