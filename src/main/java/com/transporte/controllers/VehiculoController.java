package com.transporte.controllers;

import com.transporte.constants.Constants;
import com.transporte.dto.RegisterConductorRequest;
import com.transporte.dto.RegisterDatosVehiculoRequest;
import com.transporte.dto.RegisterVehiculoConductorRequest;
import com.transporte.dto.RegisterVehiculoRequest;
import com.transporte.models.ResponseService;
import com.transporte.services.ConductorService;
import com.transporte.services.VehiculoConductorService;
import com.transporte.services.VehiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {
    @Autowired
    private VehiculoService vehiculoService;

    @Autowired
    private ConductorService conductorService;

    @Autowired
    private VehiculoConductorService vehiculoConductorService;

    @PostMapping("/register")
    public ResponseEntity<ResponseService> registerVehiculo(@RequestBody @Valid RegisterVehiculoRequest registerVehiculoRequest, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return validation(bindingResult);
        }
        ResponseService registeredVehiculo = vehiculoService.registerOneVehiculo(registerVehiculoRequest);
        return new ResponseEntity<ResponseService>(registeredVehiculo, HttpStatus.OK);
    }

    @PostMapping("/datos/register")
    public ResponseEntity<ResponseService> registerDatos(@RequestBody @Valid RegisterDatosVehiculoRequest registerDatosVehiculoRequest, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return validation(bindingResult);
        }
        ResponseService registeredData = vehiculoService.registerDataVehiculo(registerDatosVehiculoRequest);
        return new ResponseEntity<ResponseService>(registeredData, HttpStatus.OK);
    }

    @PostMapping("/conductor")
    public ResponseEntity<ResponseService> registerConductor(@RequestBody @Valid RegisterConductorRequest registerConductorRequest, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        ResponseService response = conductorService.registerConductor(registerConductorRequest);
        return new ResponseEntity<ResponseService>(response, HttpStatus.OK);
    }

    @PostMapping("/conductores")
    public ResponseEntity<ResponseService> registerVehiculoConductor(@RequestBody @Valid RegisterVehiculoConductorRequest vehiculoConductor, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        ResponseService response = vehiculoConductorService.registerVehiculoConductor(vehiculoConductor);
        return new ResponseEntity<ResponseService>(response, HttpStatus.OK);
    }

    private ResponseEntity<ResponseService> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return new ResponseEntity<ResponseService>(new ResponseService(Constants.RESPONSE_TYPE_ERROR, Constants.FALTAN_CAMPOS, errors), HttpStatus.OK);
    }
}
