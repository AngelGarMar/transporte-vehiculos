package com.transporte.services;

import com.transporte.dto.RegisterDatosVehiculoRequest;
import com.transporte.dto.RegisterVehiculoRequest;
import com.transporte.models.ResponseService;
import jakarta.validation.Valid;

public interface VehiculoService {
    ResponseService registerOneVehiculo(RegisterVehiculoRequest registerVehiculoRequest);

    ResponseService registerDataVehiculo(@Valid RegisterDatosVehiculoRequest registerDatosVehiculoRequest);
}
