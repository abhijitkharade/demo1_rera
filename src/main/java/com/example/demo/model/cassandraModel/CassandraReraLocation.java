package com.example.demo.model.cassandraModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.*;

import java.time.LocalDate;


@Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Table("rera_locations")
    public class CassandraReraLocation {

        @PrimaryKey
        private CassandraReraLocationKey key;

        @Column(value = "apartment_type")
        private String apartmentType;

        @Column(value = "asset_type")
        private String assetType;

        @Column(value = "city")
        private String city;

        @Column(value = "development_Status")
        private String developmentStatus;

        @Column(value = "development_status_in_percentage")
        private Double developmentStatusInPercentage;

        @Column(value = "event_month")
        private String eventMonth;

        @Column(value = "location")
        private String location;

        @Column(value = "no_of_apartment")
        private Integer noOfApartment;

        @Column(value = "no_of_booked_apartment")
        private Integer noOfBookedApartment;

        @Column(value = "percentage_of_booking")
        private Double percentageOfBooking;

        @Column(value = "proposed_date_of_completion")
        private LocalDate proposedDateOfCompletion;

        @Column(value = "rate")
        private Float rate;

        @Column(value = "transaction_category")
        private String transactionCategory;

        @Column(value = "transaction_type")
        private String transactionType;




    }
