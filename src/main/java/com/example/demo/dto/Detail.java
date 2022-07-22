package com.example.demo.dto;

import com.example.demo.model.cassandraModel.CassandraReraDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Detail implements Serializable {
    private String id;
    private String reraId;
    private TowerName towerName;
    private LocalDate proposedDateOfCompletion;
    private Integer noOfBasement;
    private Integer noOfPlinth;
    private Integer noOfPodium;
    private Integer noOfSanctionedFloors;
    private Integer noOfClosedParking;
    private Float developmentStatus;


    public Detail(List<CassandraReraDetails> details,String  reraId){

        details= details.stream().filter(e->e.getKey().getReraId().equals(reraId)).collect(Collectors.toList());

        this.id=details.get(0).getKey().getBuildingId();
        this.reraId=reraId;

        this.proposedDateOfCompletion= details.get(0).getProposedCompletionDate();
        try {
            this.noOfBasement=(int)(details.get(0).getNoOfBasements());
 //           System.out.println(details.get(0).getNoOfBasements());
        } catch (Exception e) {
            this.noOfBasement=0;
        }

        try {
            this.noOfPlinth=details.get(0).getNoOfPlinth();
            if(this.noOfPlinth ==0);
        } catch (Exception e) {
            this.noOfPlinth=0;
        }
        try {
            this.noOfPodium=details.get(0).getNoOfPodium();
            if(this.noOfPodium==0);
        } catch (Exception e) {
            this.noOfPodium=0;
        }
        try {
            this.noOfSanctionedFloors=details.get(0).getNoOfSlab();
            if (this.noOfSanctionedFloors==0);
        } catch (Exception e) {
            this.noOfSanctionedFloors=0;
        }
        try {
            this.noOfClosedParking=details.get(0).getNoOfClosedParking();
            if ((this.noOfClosedParking==0));

        } catch (Exception e) {
            this.noOfClosedParking=0;
        }
        try {
            this.developmentStatus=details.get(0).getDevelopmentStatus();
            if (this.developmentStatus==0);
        } catch (Exception e) {
            this.developmentStatus=0.0f;
        }

        towerName = new TowerName(details);
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
 class TowerName  {
    private String name;
//    private  ApartmentType apartmentType;
    private List< ApartmentType> apartments
        ;


    public TowerName (List <CassandraReraDetails> details){
        this.name = details.get(0).getTowerName();

        this.apartments= new ArrayList<>();
        Set<String> apartmentTypeList= details.stream().map(CassandraReraDetails::getApartmentType).collect(Collectors.toSet());

        for (String apartmentType: apartmentTypeList){
           String area="0-12";
           Double minArea=0.0;
           Double maxArea=0.0;
           boolean flag= true;

           Integer noOfApartment=0;
           Integer noOfBookedApartments=0;
           Double percentOfBooking =0.0;

            for (CassandraReraDetails detail:details ){
                if(detail.getApartmentType().equals(apartmentType)){
                    noOfApartment=noOfApartment + detail.getNoOfApartment();
                    noOfBookedApartments = noOfBookedApartments + detail.getNoOfBookedApartment();

                    if(flag) {
                        minArea = detail.getCarpetArea();
                        maxArea = detail.getCarpetArea();
                        flag=false;
                    }else {
                        if(minArea > detail.getCarpetArea())
                            minArea=detail.getCarpetArea();
                        if(maxArea<detail.getCarpetArea())
                            maxArea=detail.getCarpetArea();
                    }
                }
            }
            flag=true;
                minArea=minArea*10.76391042;
                maxArea=maxArea*10.76391042;
            area=String.valueOf((int)((double)(minArea))) + "-" + String.valueOf((int)((double)(maxArea))) +" sqft";

            try {
                percentOfBooking = (double) (noOfBookedApartments * 100 / noOfApartment);
            } catch (Exception e) {
                percentOfBooking=0.0;
            }

            this.apartments.add(new ApartmentType(apartmentType,area,noOfApartment,noOfBookedApartments,percentOfBooking));
        }




    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class ApartmentType  {
    private  String apartmentType;
    private String area;
     private Integer noOfApartment;
    private Integer noOfBookedApartment;
    private Double percentOfBooking;

    public ApartmentType (CassandraReraDetails details){

        this.area=String.valueOf(details.getArea());
        this.noOfApartment=details.getNoOfApartment();
        this.noOfBookedApartment=details.getNoOfBookedApartment();

        try {
            int booked = details.getNoOfBookedApartment();

            int total = details.getNoOfApartment();
            this.percentOfBooking = (double) (booked * 100 / total);
        }catch(Exception e){
            this.percentOfBooking=0.00;
        }
    }
}
