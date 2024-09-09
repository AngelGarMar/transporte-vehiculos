package com.transporte.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "vehiculos_conductores")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VehiculoConductor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creado")
    private LocalDateTime creado;
    @Column(name = "estatus")
    private int estatus;
    @Column(name = "bloqueado")
    @NotNull
    private int bloqueado;
    @Column(name = "en_curso")
    @NotNull
    private int enCurso;
    @Column(name = "disponible")
    @NotNull
    private int disponible;
    @ManyToOne
    @JoinColumn(name = "conductor_id")
    private Conductor conductorId;
    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculoId;
}
