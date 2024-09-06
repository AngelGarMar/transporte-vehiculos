package com.transporte.services;

import com.transporte.dto.RegisterVehiculoRequest;
import com.transporte.models.ResponseService;

public interface VehiculoService {
    ResponseService registerOneVehiculo(RegisterVehiculoRequest registerVehiculoRequest);
}
