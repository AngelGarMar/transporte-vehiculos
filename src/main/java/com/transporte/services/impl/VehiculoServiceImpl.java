package com.transporte.services.impl;

import com.transporte.constants.Constants;
import com.transporte.dto.RegisterDatosVehiculoRequest;
import com.transporte.dto.RegisterDatosVehiculoResponse;
import com.transporte.dto.RegisterVehiculoRequest;
import com.transporte.dto.RegisterVehiculoResponse;
import com.transporte.entities.DatosVehiculo;
import com.transporte.entities.Vehiculo;
import com.transporte.models.ResponseService;
import com.transporte.repositories.DatosVehiculoRepository;
import com.transporte.repositories.VehiculoRepository;
import com.transporte.services.VehiculoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Service
@Slf4j
public class VehiculoServiceImpl implements VehiculoService {
    @Autowired
    private VehiculoRepository vehiculoRepository;
    @Autowired
    private DatosVehiculoRepository datosVehiculoRepository;

    @Override
    @Transactional(readOnly = false)
    public ResponseService registerOneVehiculo(RegisterVehiculoRequest registerVehiculoRequest) {
        Optional<Vehiculo> vehiculoOp = vehiculoRepository.findByNombreAndEstatus(registerVehiculoRequest.getNombre(), 1);
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

    @Override
    @Transactional(readOnly = false)
    public ResponseService registerDataVehiculo(RegisterDatosVehiculoRequest registerDatosVehiculoRequest) {
        Optional<Vehiculo> vehiculoOp = vehiculoRepository.findById(registerDatosVehiculoRequest.getVehiculoId());
        if (vehiculoOp.isEmpty()) {
            return new ResponseService(Constants.RESPONSE_TYPE_ERROR, Constants.VEHICULO_NO_EXISTE, null);
        }
        Vehiculo vehiculoDb = vehiculoOp.get();
        Optional<DatosVehiculo> datosVehiculoOp = datosVehiculoRepository.findByVehiculoId(vehiculoDb);
        if (datosVehiculoOp.isPresent()) {
            return new ResponseService(Constants.RESPONSE_TYPE_ERROR, Constants.DATOS_VEHICULO_EXISTEN, null);
        }
        RegisterVehiculoResponse vehiculoResponse = new RegisterVehiculoResponse(vehiculoDb);
        DatosVehiculo datosVehiculo = DatosVehiculo.builder()
                .asientos(registerDatosVehiculoRequest.getAsientos())
                .placa(registerDatosVehiculoRequest.getPlaca())
                .numeco(registerDatosVehiculoRequest.getNumeco())
                .creado(LocalDateTime.now(ZoneId.of("America/Mexico_City")))
                .estatus(1)
                .vehiculoId(vehiculoDb)
                .build();
        datosVehiculo = datosVehiculoRepository.save(datosVehiculo);
        RegisterDatosVehiculoResponse response = new RegisterDatosVehiculoResponse(datosVehiculo, vehiculoResponse);
        return new ResponseService(Constants.RESPONSE_TYPE_SUCCESS, Constants.DATOS_VEHICULO_GUARDADO, response);
    }
}
