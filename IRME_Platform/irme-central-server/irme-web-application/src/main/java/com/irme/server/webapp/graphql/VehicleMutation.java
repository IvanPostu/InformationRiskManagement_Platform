package com.irme.server.webapp.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.irme.server.webapp.dao.VehicleResultDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
public class VehicleMutation implements GraphQLMutationResolver {

    @Autowired
    private VehicleService vehicleService;

    @PreAuthorize("!isAnonymous()")
    public VehicleResultDAO createVehicle(final String type, final String modelCode,
            final String brandName, final String launchDate) {
        return this.vehicleService.createVehicle(type, modelCode, brandName, launchDate);
    }
}
