package com.transporte.services.impl;

import com.transporte.constants.Constants;
import com.transporte.dto.RegisterVehiculoConductorRequest;
import com.transporte.entities.Conductor;
import com.transporte.entities.Vehiculo;
import com.transporte.entities.VehiculoConductor;
import com.transporte.models.ResponseService;
import com.transporte.repositories.ConductorRepository;
import com.transporte.repositories.VehiculoConductorRepository;
import com.transporte.repositories.VehiculoRepository;
import com.transporte.services.VehiculoConductorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class VehiculoConductorServiceImpl implements VehiculoConductorService {
    @Autowired
    private ConductorRepository conductorRepository;
    @Autowired
    private VehiculoRepository vehiculoRepository;
    @Autowired
    private VehiculoConductorRepository vehiculoConductorRepository;

    @Override
    @Transactional(readOnly = false)
    public ResponseService registerVehiculoConductor(RegisterVehiculoConductorRequest vehiculoConductor) {
        //revisar que existe el conductor
        Optional<Conductor> conductorOp = conductorRepository.findById(vehiculoConductor.getConductorId());
        if (conductorOp.isEmpty()) {
            return new ResponseService(Constants.RESPONSE_TYPE_ERROR, Constants.CONDUCTOR_NO_EXISTE, null);
        }
        Conductor conductorDb = conductorOp.get();
        //revisar que exista el vehiculo
        Optional<Vehiculo> vehiculoOp = vehiculoRepository.findById(vehiculoConductor.getVehiculoId());
        if (vehiculoOp.isEmpty()) {
            return new ResponseService(Constants.RESPONSE_TYPE_ERROR, Constants.VEHICULO_NO_EXISTE, null);
        }
        Vehiculo vehiculoDb = vehiculoOp.get();
        //revisar que el conductor no esté en curso
        Optional<VehiculoConductor> vehiculoConductorOp = vehiculoConductorRepository.findByEnCursoAndEstatusAndConductorId(1, 1, conductorDb);
        if (vehiculoConductorOp.isPresent()) {
            return new ResponseService(Constants.RESPONSE_TYPE_ERROR, Constants.CONDUCTOR_EN_CURSO, null);
        }
        //obtener el conductor con su relación de vehiculo
        Optional<VehiculoConductor> vehiculoConductorOp2 = vehiculoConductorRepository.findByConductorIdAndEstatus(conductorDb, 1);
        //si existe el registro, se actualiza con estatus cero
        if (vehiculoConductorOp2.isPresent()) {
            VehiculoConductor vehiculoConductorDb = vehiculoConductorOp2.get();
            vehiculoConductorDb.setEstatus(0);
            vehiculoConductorRepository.save(vehiculoConductorDb);
            //se inserta un nuevo registro con el conductor y vehiculo
            VehiculoConductor vehiculoConductorDb2 = VehiculoConductor.builder()
                    .creado(LocalDateTime.now(ZoneId.of("America/Mexico_City")))
                    .estatus(1)
                    .bloqueado(0)
                    .enCurso(0)
                    .disponible(1)
                    .conductorId(conductorDb)
                    .vehiculoId(vehiculoDb)
                    .build();
            vehiculoConductorRepository.save(vehiculoConductorDb2);
        } else {
            //si no existe el registro, se inserta uno nuevo
            VehiculoConductor vehiculoConductorDb2 = VehiculoConductor.builder()
                    .creado(LocalDateTime.now(ZoneId.of("America/Mexico_City")))
                    .estatus(1)
                    .bloqueado(0)
                    .enCurso(0)
                    .disponible(1)
                    .conductorId(conductorDb)
                    .vehiculoId(vehiculoDb)
                    .build();
            vehiculoConductorRepository.save(vehiculoConductorDb2);
        }
        return new ResponseService(Constants.RESPONSE_TYPE_SUCCESS, Constants.CONDUCTOR_VEHICULO_GUARDADO, null);
    }
}
