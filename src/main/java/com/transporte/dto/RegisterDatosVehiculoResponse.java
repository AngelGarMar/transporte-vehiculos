package com.transporte.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.transporte.entities.DatosVehiculo;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisterDatosVehiculoResponse {
    private Long id;
    private int asientos;
    private String placa;
    private String numeco;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime creado;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime modificado;
    private RegisterVehiculoResponse vehiculo;

    public RegisterDatosVehiculoResponse(DatosVehiculo datosVehiculo, RegisterVehiculoResponse vehiculoResponse) {
        this.id = datosVehiculo.getId();
        this.asientos = datosVehiculo.getAsientos();
        this.placa = datosVehiculo.getPlaca();
        this.numeco = datosVehiculo.getNumeco();
        this.creado = datosVehiculo.getCreado();
        this.vehiculo = vehiculoResponse;
    }
}
