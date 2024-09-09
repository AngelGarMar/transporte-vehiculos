package com.transporte.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterVehiculoConductorRequest implements Serializable {
    @NotNull
    private Long conductorId;
    @NotNull
    private Long vehiculoId;
}
