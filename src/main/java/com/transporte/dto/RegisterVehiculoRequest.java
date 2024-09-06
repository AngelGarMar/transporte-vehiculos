package com.transporte.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterVehiculoRequest implements Serializable {
    @Size(min = 1, max = 45)
    @NotBlank
    private String nombre;
}
