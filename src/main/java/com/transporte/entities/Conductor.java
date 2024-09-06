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
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cat_conductores")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Conductor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 1, max = 45)
    @Column(name = "nombre")
    private String nombre;
    @NotBlank
    @Size(min = 1, max = 45)
    @Column(name = "apaterno")
    private String apaterno;
    @NotBlank
    @Size(min = 1, max = 45)
    @Column(name = "amaterno")
    private String amaterno;
    @NotNull
    private int edad;
    @NotBlank
    @Size(min = 10, max = 10)
    @Column(name = "celular")
    private String celular;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creado")
    private Date creado;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modificado")
    private Date modificado;
    @Column(name = "estatus")
    private int estatus;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "conductorId")
    private List<VehiculoConductor> vehiculoConductor;
}
