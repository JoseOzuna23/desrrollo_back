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
@Table(name = "distritos", uniqueConstraints = {
    @UniqueConstraint(name = "uk_ditritos_codigo_sicoop", columnNames = "codigo_sicoop")
})
public class Distrito implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_distrito")
    private Integer codDistrito;

    @Column(name = "fecha_sistema", nullable = false, columnDefinition = "date default CURRENT_DATE")
    private Date fechaSistema = new Date();

    @Column(name = "visible", nullable = false, columnDefinition = "boolean default true")
    private Boolean visible = true;

    @Column(name = "descripcion", length = 100)
    private String descripcion;

    @Column(name = "codigo_set")
    private Integer codigoSet;

    @Column(name = "codigo_sicoop", unique = true)
    private Integer codigoSicoop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_departamento", referencedColumnName = "cod_departamento", foreignKey = @ForeignKey(name = "distritos_cod_departamento_fkey"))
    private Departamento departamento;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "usuario", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_usuario"))
//    private Usuarios usuario;


}
