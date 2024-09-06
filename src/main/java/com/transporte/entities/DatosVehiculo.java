package com.transporte.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "datos_vehiculo")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DatosVehiculo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private int asientos;
    @NotBlank
    @Size(min = 8, max = 8)
    private String placa;
    @NotBlank
    @Size(min = 3, max = 3)
    private String numeco;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime creado;
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificado;
    @Column(name = "estatus")
    private int estatus;
    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    @JsonIgnoreProperties({"datosVehiculo", "hanlder", "hibernateLazyInitializer"})
    private Vehiculo vehiculoId;
}
