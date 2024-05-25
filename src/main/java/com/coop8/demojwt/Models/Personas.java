package com.coop8.demojwt.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.sql.Date;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "personas")
public class Personas implements Serializable {

    private static final long serialVersionUID = 8510122407262613133L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "SERIAL")
    private Long id;

    @Column(name = "nro_docu", length = 15, unique = true)
    private String nroDocumento;

    @Column(name = "nombre_1", length = 30)
    private String nombre1;

    @Column(name = "nombre_2", length = 30)
    private String nombre2;

    @Column(name = "nombre_3", length = 30)
    private String nombre3;

    @Column(name = "apellido_1", length = 30)
    private String apellido1;

    @Column(name = "apellido_2", length = 30)
    private String apellido2;

    @Column(name = "apellido_3", length = 30)
    private String apellido3;

    @Column(name = "fecha_nac")
    private Date fechaNacimiento;

    @Column(name = "sexo", length = 1)
    private String sexo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_docu", referencedColumnName = "id")
	private TiposDocumentos tipoDocumento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_persona", referencedColumnName = "id")
    private TiposPersonas tipoPersona;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado_civil", referencedColumnName = "id")
    private EstadosCiviles estadoCivil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cod_ciudad", referencedColumnName = "cod_ciudad")
    private Ciudad ciudad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nacionalidad", referencedColumnName = "id")
    private Nacionalidades nacionalidad;

    @Column(name = "fechasys")
    private Date fechasys;

    @Column(name = "usuariosys", length = 50)
    private String usuariosys;
}
