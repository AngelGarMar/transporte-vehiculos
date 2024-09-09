package com.transporte.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterConductorRequest implements Serializable {
    @Size(min = 1, max = 45)
    @NotBlank
    private String nombre;
    @Size(min = 1, max = 45)
    @NotBlank
    private String apaterno;
    @Size(min = 1, max = 45)
    @NotBlank
    private String amaterno;
    @NotNull
    private int edad;
    @NotBlank
    @Size(min = 10, max = 10)
    private String celular;
}
