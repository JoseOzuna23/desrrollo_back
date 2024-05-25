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
@Table(name = "ciudades", uniqueConstraints = {
    @UniqueConstraint(name = "uk_ciudades_codigo_sicoop", columnNames = "codigo_sicoop")
})
public class Ciudad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_ciudad")
    private Integer codCiudad;

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
    @JoinColumn(name = "cod_distrito", referencedColumnName = "cod_distrito", foreignKey = @ForeignKey(name = "fk_ciudad_distrito"))
    private Distrito distrito;
    
public Ciudad(Integer codCiudad, Date fechaSistema, Boolean visible, String descripcion) {
    this.codCiudad = codCiudad;
    this.fechaSistema = fechaSistema;
    this.visible = visible;
    this.descripcion = descripcion;
}


}
