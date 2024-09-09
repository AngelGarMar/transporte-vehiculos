package com.transporte.repositories;

import com.transporte.entities.Conductor;
import com.transporte.entities.VehiculoConductor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehiculoConductorRepository extends JpaRepository<VehiculoConductor, Long> {
    Optional<VehiculoConductor> findByEnCursoAndEstatusAndConductorId(int enCurso, int estatus, Conductor conductorId);
    Optional<VehiculoConductor> findByConductorIdAndEstatus(Conductor conductorId, int estatus);
}
