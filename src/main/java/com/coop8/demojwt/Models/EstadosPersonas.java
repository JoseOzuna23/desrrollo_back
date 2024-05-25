package com.coop8.demojwt.Models;

import java.io.Serializable;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "estados_personas")
public class EstadosPersonas implements Serializable {

	private static final long serialVersionUID = 8510122407262613133L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "SERIAL")
	private Long id;


	@Column(name = "descripcion", unique = true, length = 100)
	private String descripcion;

	@Column(name = "usuariosys", length = 50)
	
	private String usuariosys;
}
