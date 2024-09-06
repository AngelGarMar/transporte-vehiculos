package com.transporte.entities;

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
import java.util.List;

@Entity
@Table(name = "cat_vehiculos")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Vehiculo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime creado;
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificado;
    @NotNull
    @Column(name = "estatus")
    private int estatus;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "vehiculoId")
    private List<VehiculoConductor> vehiculoConductor;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "vehiculoId")
    private List<DatosVehiculo> datosVehiculo;
}
