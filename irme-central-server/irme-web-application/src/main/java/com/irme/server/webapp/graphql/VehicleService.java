package com.irme.server.webapp.graphql;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.irme.server.webapp.dao.VehicleResultDAO;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    private final LinkedList<VehicleResultDAO> internalVehicles = new LinkedList<>();

    static int id_incrementor = 0;

    public VehicleResultDAO createVehicle(final String type, final String modelCode,
            final String brandName, final String launchDate) {
        final VehicleResultDAO vehicle = new VehicleResultDAO();
        vehicle.setId(id_incrementor++);
        vehicle.setType(type);
        vehicle.setModelCode(modelCode);
        vehicle.setBrandName(brandName);
        vehicle.setLaunchDate(LocalDate.parse(launchDate));

        internalVehicles.add(vehicle);

        return vehicle;
    }

    public List<VehicleResultDAO> getAllVehicles(final int count) {
        int localCounter = 0;
        List<VehicleResultDAO> resultList = new ArrayList<>(count);
        Iterator<VehicleResultDAO> vehicleIterator = internalVehicles.iterator();

        while (vehicleIterator.hasNext() && localCounter++ < count) {
            resultList.add(vehicleIterator.next());
        }

        return internalVehicles.stream().limit(count).collect(Collectors.toList());
    }

    public Optional<VehicleResultDAO> getVehicle(final int id) {

        VehicleResultDAO vehicle = null;

        for (VehicleResultDAO v : internalVehicles) {
            if (v.getId() == id) {
                vehicle = v;
                break;
            }
        }

        return Optional.ofNullable(vehicle);
    }
}
