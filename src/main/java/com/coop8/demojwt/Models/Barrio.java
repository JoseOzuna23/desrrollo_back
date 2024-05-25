package com.coop8.demojwt.Models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "barrios")
public class Barrio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_barrio")
    private Integer codBarrio;

    @Column(name = "fecha_sistema", nullable = false, columnDefinition = "date default CURRENT_DATE")
    private Date fechaSistema = new Date();

    @Column(name = "visible", nullable = false, columnDefinition = "boolean default true")
    private Boolean visible = true;

    @Column(name = "descripcion", length = 100)
    private String descripcion;

    @Column(name = "codigo_set")
    private Integer codigoSet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_ciudad", referencedColumnName = "cod_ciudad", foreignKey = @ForeignKey(name = "fk_barrio_ciudad"))
    private Ciudad ciudad;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "usuario", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_usuario"))
//    private Usuarios usuario;
}
