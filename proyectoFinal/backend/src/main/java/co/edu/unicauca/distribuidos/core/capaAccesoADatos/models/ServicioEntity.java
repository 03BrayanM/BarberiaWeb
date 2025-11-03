package co.edu.unicauca.distribuidos.core.capaAccesoADatos.models;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServicioEntity {
	private Integer id;
	private String nombre;
	private String descripcion;
	private BigDecimal  precio;
	private Integer duracion;
	private String imagen; // ruta a direccion de ubicacion"
	private boolean estado;
	private CategoriaEntity objCategoria;

	public ServicioEntity() {

	}
}
