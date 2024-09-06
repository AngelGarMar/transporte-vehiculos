package com.transporte.repositories;

import com.transporte.entities.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    Optional<Vehiculo> findByNombreAndEstatus(String nombre, int estatus);
    Optional<Vehiculo> findById(Long id);
}
