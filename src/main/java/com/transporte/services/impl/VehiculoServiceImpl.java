package com.transporte.services.impl;

import com.transporte.constants.Constants;
import com.transporte.dto.RegisterVehiculoRequest;
import com.transporte.dto.RegisterVehiculoResponse;
import com.transporte.entities.Vehiculo;
import com.transporte.models.ResponseService;
import com.transporte.repositories.VehiculoRepository;
import com.transporte.services.VehiculoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class VehiculoServiceImpl implements VehiculoService {
    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Override
    public ResponseService registerOneVehiculo(RegisterVehiculoRequest registerVehiculoRequest) {
        Optional<Vehiculo> vehiculoOp = vehiculoRepository.findByNombre(registerVehiculoRequest.getNombre());
        if (vehiculoOp.isPresent()) {
            return new ResponseService(Constants.RESPONSE_TYPE_ERROR, Constants.VEHICULO_EXISTENTE, null);
        }
        Vehiculo vehiculoDb = Vehiculo.builder()
                .nombre(registerVehiculoRequest.getNombre())
                .creado(LocalDateTime.now(ZoneId.of("America/Mexico_City")))
                .estatus(1)
                .build();
        vehiculoDb = vehiculoRepository.save(vehiculoDb);
        RegisterVehiculoResponse vehiculoResponse = new RegisterVehiculoResponse(vehiculoDb);
        return new ResponseService(Constants.RESPONSE_TYPE_SUCCESS, Constants.VEHICULO_GUARDADO, vehiculoResponse);
    }
}
