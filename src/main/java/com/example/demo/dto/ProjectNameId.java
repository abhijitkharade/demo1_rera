package com.example.demo.dto;

import com.example.demo.model.cassandraModel.CassandraReraDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectNameId {
    private String projectName;
    private String  buildingId;

    public ProjectNameId(CassandraReraDetails details){
        this.projectName= details.getProjectName();
        this.buildingId=details.getKey().getBuildingId();
    }
}
