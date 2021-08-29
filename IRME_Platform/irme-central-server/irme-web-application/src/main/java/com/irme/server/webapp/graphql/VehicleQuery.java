package com.irme.server.webapp.graphql;

import java.util.List;
import java.util.Optional;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.irme.server.webapp.dao.VehicleResultDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
public class VehicleQuery implements GraphQLQueryResolver {

    @Autowired
    private VehicleService vehicleService;

    @PreAuthorize("isAuthenticated()")
    public List<VehicleResultDAO> getVehicles(final int count, final String s) {
        return this.vehicleService.getAllVehicles(count);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public Optional<VehicleResultDAO> getVehicle(final int id) {
        return this.vehicleService.getVehicle(id);
    }


}
