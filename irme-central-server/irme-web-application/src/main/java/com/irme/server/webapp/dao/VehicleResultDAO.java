package com.irme.server.webapp.dao;


import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class VehicleResultDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    private String type;

    private String modelCode;

    private String brandName;

    private LocalDate launchDate;

    private transient String formattedDate;

}
