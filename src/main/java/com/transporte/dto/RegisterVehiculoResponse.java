package com.transporte.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.transporte.entities.Vehiculo;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class RegisterVehiculoResponse {
    private Long id;
    private String nombre;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime creado;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date modificado;

    public RegisterVehiculoResponse(Vehiculo vehiculo) {
        this.id = vehiculo.getId();
        this.nombre = vehiculo.getNombre();
        this.creado = vehiculo.getCreado();
        this.modificado = vehiculo.getModificado();
    }
}
