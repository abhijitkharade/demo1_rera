package com.example.demo.model.cassandraModel;

import jnr.ffi.annotations.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import javax.persistence.Entity;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Entity
@Table("rera_details_yukta")
public class CassandraReraDetails {
//    @PrimaryKey
//    @Column("building_id")
//    private  String buildingId;
//    @Column("area_id")
//    private String areaId;
//    @Column("id")
//    private String id;

    @PrimaryKey
    private CassandraReraDetailsKey key;


    @Column("aggregate_area")
    private Double AggregateArea;

    @Column("apartment_mortgage_area")
    private  Float apartmentMortgageArea;

    @Column("apartment_type")
    private String apartmentType;

    @Column("approved_build_up_area")
    private Float ApprovedBuildUpArea;

    @Column("approved_date")
    private LocalDate approvedDate;
    @Column("area")
    private Float area;
    @Column("authority_name")
    private String authorityName;
    @Column("bank_ifsc")
    private String BankIfsc;
    @Column("bank_name")
    private String bankName;
    @Column("boundary_details")
    private String BoundaryDetails;
    @Column("building_mortgage_area")
    private Double BuildingMortgageArea;
    @Column("building_name")
    private String buildingName;
    @Column("carpet_area")
    private Double carpetArea;
    @Column("city")
    private String city;
    @Column("covered_parking_details")
    private String coveredParkingDetails;
    @Column("development_status")
    private Float developmentStatus;
    @Column("district")
    private String district;
    @Column("division")
    private String division;
    @Column("expected_date_of_completion")
    private LocalDate  expectedDateOfCompletion;
    @Column("floor_id")
    private String floorId;
    @Column("garage_details")
    private String garageDetails;
    @Column("locality")
    private String locality;
    @Column("location")
    private  String location;
//    @Column("mortgage_area")
//    private Float mortgageArea;
    @Column("net_area")
    private Float netArea;
    @Column("no_of_apartment")
    private Integer noOfApartment;
    @Column("no_of_basements")
    private Integer noOfBasements;
    @Column("no_of_booked_apartment")
    private Integer noOfBookedApartment;
    @Column("no_of_closed_parking")
    private Integer noOfClosedParking;
    @Column("no_of_open_parking")
    private Integer onOfOpenParking;
    @Column("no_of_plinth")
    private Integer noOfPlinth;
    @Column("no_of_podium")
    private Integer noOfPodium;
    @Column("no_of_slab")
    private Integer noOfSlab;
    @Column("no_of_slits")
    private Integer noOfSlits;
    @Column("not_sanctioned_building_count")
    private Integer notSanctionedBuildingCount;
    @Column("open_parking_details")
    private String openParkingDetails;
    @Column("pin_code")
    private Integer pinCode;
    @Column("project_mortgage_area")
    private Double projectMortgageArea;
    @Column("project_name")
    private String projectName;
    @Column("project_status")
    private String projectStatus;
    @Column("project_type")
    private String projectType;
    @Column("proposed_build_up_area")
    private Float proposedBuildUpArea;
    @Column("proposed_building_units")
    private Integer proposedBuildingUnits;
    @Column("proposed_completion_date")
    private LocalDate proposedCompletionDate;
    @Column("record_status")
    private String recordStatus;
    @Column("revised_proposed_completion_date")
    private LocalDate revisedProposedCompletionDate;
    @Column("road_widening_area")
    private Float roadWideningArea;
    @Column("sanctioned_building_count")
    private Integer sanctionedBuildingCount;
    @Column("sanctioned_by_mcgm")
    private String sanctionedByMcgm;
    @Column("state")
    private String state;
    @Column("street")
    private String street;
    @Column("sy_number")
    private String syNumber;
    @Column("taluka")
    private String taluka;
    @Column("total_building_count")
    private Integer totalBuildingCount;
    @Column("total_fsi")
    private Double totalFsi;
    @Column("total_parking_area")
    private Float totalParkingArea;
    @Column("tower_name")
    private String towerName;
    @Column("village")
    private String village;

}
