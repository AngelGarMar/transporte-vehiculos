package com.transporte.controllers;

import com.transporte.constants.Constants;
import com.transporte.dto.RegisterDatosVehiculoRequest;
import com.transporte.dto.RegisterVehiculoRequest;
import com.transporte.models.ResponseService;
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

    @PostMapping("/register")
    public ResponseEntity<ResponseService> register(@RequestBody @Valid RegisterVehiculoRequest registerVehiculoRequest, BindingResult bindingResult) {
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

    private ResponseEntity<ResponseService> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return new ResponseEntity<ResponseService>(new ResponseService(Constants.RESPONSE_TYPE_ERROR, Constants.FALTAN_CAMPOS, errors), HttpStatus.OK);
    }
}
