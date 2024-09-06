package com.transporte.repositories;

import com.transporte.entities.DatosVehiculo;
import com.transporte.entities.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DatosVehiculoRepository extends JpaRepository<DatosVehiculo, Long> {
    Optional<DatosVehiculo> findByVehiculoId(Vehiculo vehiculoId);
}
