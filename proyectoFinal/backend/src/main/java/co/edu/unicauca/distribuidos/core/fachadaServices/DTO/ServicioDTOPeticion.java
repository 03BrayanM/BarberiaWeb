package co.edu.unicauca.distribuidos.core.fachadaServices.DTO;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServicioDTOPeticion {
	private Integer id;
	private String nombre;
	private String descripcion;
	private BigDecimal precio;
	private Integer duracion;
	private String imagen;
	private boolean estado;
	private CategoriaDTOPeticion objCategoria;

}