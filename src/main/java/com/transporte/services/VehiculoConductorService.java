package com.transporte.services;

import com.transporte.dto.RegisterVehiculoConductorRequest;
import com.transporte.models.ResponseService;

public interface VehiculoConductorService {
    ResponseService registerVehiculoConductor(RegisterVehiculoConductorRequest vehiculoConductor);
}
