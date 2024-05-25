package com.coop8.demojwt.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "tipos_documentos")
public class TiposDocumentos implements Serializable {

    private static final long serialVersionUID = 8510122407262613133L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "SERIAL")
    private Long id;

    @NotNull
    @Column(name = "descripcion", unique = true, length = 100)
    private String descripcion;

    @Column(name = "usuariosys", length = 50)
    @NotNull
    private String usuariosys;

// Constructor que acepta un argumento de tipo Long
    public TiposDocumentos(Long id) {
        this.id = id;
    }

    // Constructor que acepta todos los argumentos
    public TiposDocumentos(Long id, String descripcion, String usuariosys) {
        this.id = id;
        this.descripcion = descripcion;
        this.usuariosys = usuariosys;
    }
}