package com.transporte.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.transporte.entities.Conductor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class RegisterConductorResponse implements Serializable {
    private Long id;
    private String nombre;
    private String apaterno;
    private String amaterno;
    private int edad;
    private String celular;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime creado;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime modificado;

    public RegisterConductorResponse(Conductor conductor) {
        this.id = conductor.getId();
        this.nombre = conductor.getNombre();
        this.apaterno = conductor.getApaterno();
        this.amaterno = conductor.getAmaterno();
        this.edad = conductor.getEdad();
        this.celular = conductor.getCelular();
        this.creado = conductor.getCreado();
        this.modificado = conductor.getModificado();
    }
}
