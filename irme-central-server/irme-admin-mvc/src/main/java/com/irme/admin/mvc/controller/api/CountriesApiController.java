package com.irme.admin.mvc.controller.api;

import com.irme.common.dto.CountryDto;
import com.irme.server.bll.CountryBusinessLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(value = "/api/countries")
public class CountriesApiController {

    @Autowired
    private CountryBusinessLogic countryBusinessLogic;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "")
    public ResponseEntity<?> getAll() {
        List<CountryDto> result = countryBusinessLogic.getCountries();

        return ResponseEntity.ok().body(result);
    }
}
