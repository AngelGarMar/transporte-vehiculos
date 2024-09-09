package com.transporte.services;

import com.transporte.dto.RegisterConductorRequest;
import com.transporte.models.ResponseService;

public interface ConductorService {
    ResponseService registerConductor(RegisterConductorRequest registerConductorRequest);
}
