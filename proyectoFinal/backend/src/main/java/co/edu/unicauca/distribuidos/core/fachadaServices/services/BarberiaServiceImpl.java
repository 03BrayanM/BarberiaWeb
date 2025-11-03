
package co.edu.unicauca.distribuidos.core.fachadaServices.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.ServicioEntity;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories.ServicioRepositoryBaseDatos;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.ServicioDTOPeticion;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.ServicioDTORespuesta;

@Service//El objeto creado se almacena en el contenedor de Spring
public class BarberiaServiceImpl implements IBarberiaService {

	private ServicioRepositoryBaseDatos servicioAccesoBaseDatos;	
	private ModelMapper modelMapper;

	public BarberiaServiceImpl(ServicioRepositoryBaseDatos servicioAccesoBaseDatos, ModelMapper modelMapper) {
		this.servicioAccesoBaseDatos = servicioAccesoBaseDatos;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<ServicioDTORespuesta> findAll() {
		List<ServicioDTORespuesta> listaRetornar;
		Optional<Collection<ServicioEntity>> barberiasEntityOpt = this.servicioAccesoBaseDatos.findAll();
		
		if (barberiasEntityOpt.isEmpty()) {
			listaRetornar = List.of();
		} else {
			Collection<ServicioEntity> barberiasEntity = barberiasEntityOpt.get();
			listaRetornar = this.modelMapper.map(barberiasEntity, new TypeToken<List<ServicioDTORespuesta>>() {}.getType());
		}
		
		return listaRetornar;
	}
	
	@Override
	public ServicioDTORespuesta findById(Integer id) {
		ServicioDTORespuesta barberiaRetornar = null;
		Optional<ServicioEntity> optionalBarberia = this.servicioAccesoBaseDatos.findById(id);
		if (optionalBarberia.isPresent()) {
			ServicioEntity barberiaEntity = optionalBarberia.get();
			barberiaRetornar = this.modelMapper.map(barberiaEntity, ServicioDTORespuesta.class);
		}

		return barberiaRetornar;
	}

	@Override
	public ServicioDTORespuesta save(ServicioDTOPeticion barberia) {
		ServicioEntity barberiaEntity = this.modelMapper.map(barberia, ServicioEntity.class);
		ServicioEntity objBarberiaEntity = this.servicioAccesoBaseDatos.save(barberiaEntity);
		System.out.println(objBarberiaEntity);
		ServicioDTORespuesta barberiaDTO = this.modelMapper.map(objBarberiaEntity, ServicioDTORespuesta.class);
		return barberiaDTO;
	}

	@Override
	public ServicioDTORespuesta update(Integer id, ServicioDTOPeticion barberia) {
		ServicioEntity barberiaActualizada = null;
		Optional<ServicioEntity> barberiaEntityOp = this.servicioAccesoBaseDatos.findById(id);

		if (barberiaEntityOp.isPresent()) {
			ServicioEntity objBarberiaDatosNuevos = barberiaEntityOp.get();
			objBarberiaDatosNuevos.setNombre(barberia.getNombre());
			objBarberiaDatosNuevos.setDescripcion(barberia.getDescripcion());
			objBarberiaDatosNuevos.setPrecio(barberia.getPrecio());
			objBarberiaDatosNuevos.setDuracion(barberia.getDuracion());
			objBarberiaDatosNuevos.setImagen(barberia.getImagen());
			objBarberiaDatosNuevos.setEstado(barberia.isEstado());
			objBarberiaDatosNuevos.getObjCategoria().setId(barberia.getObjCategoria().getId());
			objBarberiaDatosNuevos.getObjCategoria().setNombre("");

			Optional<ServicioEntity> optionalBarberia = this.servicioAccesoBaseDatos.update(id, objBarberiaDatosNuevos);
			barberiaActualizada = optionalBarberia.get(); 
		}

		return this.modelMapper.map(barberiaActualizada, ServicioDTORespuesta.class);
	}

	@Override
	public boolean delete(Integer id) {
		return this.servicioAccesoBaseDatos.delete(id);
	}

	@Override
	public List<ServicioDTORespuesta> findByCategoria(Integer idCategoria) {
		List<ServicioDTORespuesta> listaRetornar;
		Optional<Collection<ServicioEntity>> barberiasEntityOpt = this.servicioAccesoBaseDatos.findByCategoria(idCategoria);
		
		if (barberiasEntityOpt.isEmpty()) {
			listaRetornar = List.of();
		} else {
			Collection<ServicioEntity> barberiasEntity = barberiasEntityOpt.get();
			listaRetornar = this.modelMapper.map(barberiasEntity, new TypeToken<List<ServicioDTORespuesta>>() {}.getType());
		}
		
		return listaRetornar;
	}
}
