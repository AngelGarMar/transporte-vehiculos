package com.transporte.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisterDatosVehiculoRequest {
    @NotNull
    private int asientos;
    @Size(min = 8, max = 8)
    @NotBlank
    private String placa;
    @Size(min = 3, max = 3)
    @NotBlank
    private String numeco;
    @NotNull
    private Long vehiculoId;
}
