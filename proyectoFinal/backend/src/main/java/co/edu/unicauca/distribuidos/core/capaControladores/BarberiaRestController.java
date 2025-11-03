package co.edu.unicauca.distribuidos.core.capaControladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.ServicioDTOPeticion;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.ServicioDTORespuesta;
import co.edu.unicauca.distribuidos.core.fachadaServices.services.IBarberiaService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class BarberiaRestController {

	@Autowired
	private IBarberiaService barberiaService;

	@GetMapping("/servicios")
	public List<ServicioDTORespuesta> listarRegistros() {			
		return barberiaService.findAll();
	}

	@GetMapping("/servicios/{id}")
	public ServicioDTORespuesta consultarRegistro(@PathVariable Integer id) {
		ServicioDTORespuesta objBarberia = null;
		objBarberia = barberiaService.findById(id);
		return objBarberia;
	}
	
	@PostMapping("/servicios")
	public ServicioDTORespuesta crearRegistro(@RequestBody ServicioDTOPeticion barberia) {
		ServicioDTORespuesta objBarberia = null;
		objBarberia = barberiaService.save(barberia);
		return objBarberia;
	}

	@PutMapping("/servicios/{id}")
	public ServicioDTORespuesta actualizarRegistro(@RequestBody ServicioDTOPeticion barberia, @PathVariable Integer id) {
		ServicioDTORespuesta objBarberia = null;
		ServicioDTORespuesta barberiaActual = barberiaService.findById(id);
		if (barberiaActual != null) {
			objBarberia = barberiaService.update(id, barberia);
		}
		return objBarberia;
	}

	@DeleteMapping("/servicios/{id}")
	public Boolean eliminarRegistro(@PathVariable Integer id) {
		Boolean bandera = false;
		ServicioDTORespuesta barberiaActual = barberiaService.findById(id);
		if (barberiaActual != null) {
			bandera = barberiaService.delete(id);
		}
		return bandera;
	}

	@GetMapping("/servicios/categoria/{idCategoria}")
	public List<ServicioDTORespuesta> listarRegistroPorCategoria(@PathVariable Integer idCategoria) {
		return barberiaService.findByCategoria(idCategoria);
	}
}