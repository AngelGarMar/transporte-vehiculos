package com.transporte.services.impl;

import com.transporte.constants.Constants;
import com.transporte.dto.RegisterConductorRequest;
import com.transporte.dto.RegisterConductorResponse;
import com.transporte.entities.Conductor;
import com.transporte.models.ResponseService;
import com.transporte.repositories.ConductorRepository;
import com.transporte.services.ConductorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Service
@Slf4j
public class ConductorServiceImpl implements ConductorService {
    @Autowired
    private ConductorRepository conductorRepository;

    @Override
    @Transactional(readOnly = false)
    public ResponseService registerConductor(RegisterConductorRequest registerConductorRequest) {
        log.info("Guardad Conductor: {}", registerConductorRequest.toString());
        Optional<Conductor> conductorOp = conductorRepository
                .findByNombreAndApaternoAndAmaternoAndEstatus(
                        registerConductorRequest.getNombre(),
                        registerConductorRequest.getApaterno(),
                        registerConductorRequest.getAmaterno(),
                    1);
        if (conductorOp.isPresent()) {
            return new ResponseService(Constants.RESPONSE_TYPE_ERROR, Constants.CONDUCTOR_EXISTENTE, null);
        }
        Conductor conductorDb = Conductor.builder()
                .nombre(registerConductorRequest.getNombre())
                .apaterno(registerConductorRequest.getApaterno())
                .amaterno(registerConductorRequest.getAmaterno())
                .edad(registerConductorRequest.getEdad())
                .celular(registerConductorRequest.getCelular())
                .creado(LocalDateTime.now(ZoneId.of("America/Mexico_City")))
                .estatus(1)
                .build();
        conductorDb = conductorRepository.save(conductorDb);
        RegisterConductorResponse response = new RegisterConductorResponse(conductorDb);
        return new ResponseService(Constants.RESPONSE_TYPE_SUCCESS, Constants.CONDUCTOR_GUARDADO, response);
    }
}
