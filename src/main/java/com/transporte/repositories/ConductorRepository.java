package com.transporte.repositories;

import com.transporte.entities.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConductorRepository extends JpaRepository<Conductor, Long> {
    Optional<Conductor> findByNombreAndApaternoAndAmaternoAndEstatus(String nombre, String apaterno, String amaterno, int status);
}
