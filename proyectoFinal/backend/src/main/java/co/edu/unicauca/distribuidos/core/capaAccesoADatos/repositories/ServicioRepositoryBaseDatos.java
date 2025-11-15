package co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.CategoriaEntity;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.ServicioEntity;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories.conexion.ConexionBD;

@Repository
public class ServicioRepositoryBaseDatos {
    private final ConexionBD conexionABaseDeDatos;

    public ServicioRepositoryBaseDatos() {
        conexionABaseDeDatos = new ConexionBD();
    }

    public ServicioEntity save(ServicioEntity objServicio) {
        System.out.println("Registrando servicio de barbería en base de datos");
        ServicioEntity objServicioAlmacenado = null;
        int resultado = -1;

        try {
            conexionABaseDeDatos.conectar();

            PreparedStatement sentencia = null;
            String consulta = "INSERT INTO servicios(nombre, descripcion, precio, duracion, imagen, estado, idCategoria) VALUES(?,?,?,?,?,?,?)";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta,
                    Statement.RETURN_GENERATED_KEYS);

            sentencia.setString(1, objServicio.getNombre());
            sentencia.setString(2, objServicio.getDescripcion());
            sentencia.setBigDecimal(3, objServicio.getPrecio());
            sentencia.setInt(4, objServicio.getDuracion());
            sentencia.setString(5, objServicio.getImagen());
            sentencia.setBoolean(6, objServicio.isEstado());
            sentencia.setInt(7, objServicio.getObjCategoria().getId());

            resultado = sentencia.executeUpdate();

            ResultSet generatedKeys = sentencia.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idGenerado = generatedKeys.getInt(1);
                objServicio.setId(idGenerado);
                System.out.println("ID generado: " + idGenerado);
                if (resultado == 1) {
                    objServicioAlmacenado = this.findById(idGenerado).get();
                }
            } else {
                System.out.println("No se pudo obtener el ID generado.");
            }

            generatedKeys.close();
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
            System.out.println("Error en la inserción: " + e.getMessage());
        }

        return objServicioAlmacenado;
    }

    public Optional<Collection<ServicioEntity>> findAll() {
        System.out.println("Listando servicios de barbería desde base de datos");
        Collection<ServicioEntity> servicios = new LinkedList<ServicioEntity>();

        conexionABaseDeDatos.conectar();
        try {
            PreparedStatement sentencia = null;
            String consulta = "SELECT servicios.*, categorias.id AS idCategoria, categorias.nombre AS nombreCategoria "
                    +
                    "FROM servicios JOIN categorias ON servicios.idCategoria = categorias.id";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);
            ResultSet res = sentencia.executeQuery();

            while (res.next()) {
                ServicioEntity objServicio = mapearResultSetAServicio(res);
                servicios.add(objServicio);
            }

            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }

        return servicios.isEmpty() ? Optional.empty() : Optional.of(servicios);
    }

    public Optional<ServicioEntity> findById(Integer idServicio) {
        System.out.println("Consultando servicio de barbería desde base de datos");
        ServicioEntity objServicio = null;

        conexionABaseDeDatos.conectar();
        try {
            PreparedStatement sentencia = null;
            String consulta = "SELECT servicios.*, categorias.id AS idCategoria, categorias.nombre AS nombreCategoria "
                    +
                    "FROM servicios JOIN categorias ON servicios.idCategoria = categorias.id WHERE servicios.id = ?";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);
            sentencia.setInt(1, idServicio);
            ResultSet res = sentencia.executeQuery();

            if (res.next()) {
                System.out.println("Servicio de barbería encontrado");
                objServicio = mapearResultSetAServicio(res);
            }

            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }

        return objServicio == null ? Optional.empty() : Optional.of(objServicio);
    }

    public Optional<ServicioEntity> update(Integer idServicio, ServicioEntity objServicio) {
        System.out.println("Actualizando servicio de barbería en base de datos");
        ServicioEntity objServicioActualizado = null;
        conexionABaseDeDatos.conectar();
        int resultado = -1;

        try {
            PreparedStatement sentencia = null;
            String consulta = "UPDATE servicios SET " +
                    "nombre = ?, " +
                    "descripcion = ?, " +
                    "precio = ?, " +
                    "duracion = ?, " +
                    "imagen = ?, " +
                    "estado = ?, " +
                    "idCategoria = ? " +
                    "WHERE id = ?";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);

            sentencia.setString(1, objServicio.getNombre());
            sentencia.setString(2, objServicio.getDescripcion());
            sentencia.setBigDecimal(3, objServicio.getPrecio());
            sentencia.setInt(4, objServicio.getDuracion());
            sentencia.setString(5, objServicio.getImagen());
            sentencia.setBoolean(6, objServicio.isEstado());
            sentencia.setInt(7, objServicio.getObjCategoria().getId());
            sentencia.setInt(8, idServicio);

            resultado = sentencia.executeUpdate();
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
            System.out.println("Error en la actualización: " + e.getMessage());
        }

        if (resultado == 1) {
            objServicioActualizado = this.findById(idServicio).get();
        }

        return objServicioActualizado == null ? Optional.empty() : Optional.of(objServicioActualizado);
    }

    public boolean delete(Integer idServicio) {
        System.out.println("Desactivando servicio de barbería (eliminación lógica)");
        conexionABaseDeDatos.conectar();
        int resultado = -1;

        try {
            PreparedStatement sentencia = null;
            String consulta = "UPDATE servicios SET estado = false WHERE id = ?";
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);
            sentencia.setInt(1, idServicio);
            resultado = sentencia.executeUpdate();
            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
            System.out.println("Error en la desactivación: " + e.getMessage());
        }

        return resultado == 1;
    }

    public Optional<Collection<ServicioEntity>> findByCategoria(Integer idCategoria) {
        System.out.println("Listando servicios de barbería por categoría desde base de datos");
        Collection<ServicioEntity> servicios = new LinkedList<ServicioEntity>();

        conexionABaseDeDatos.conectar();
        try {
            PreparedStatement sentencia = null;
            String consulta = "SELECT servicios.*, categorias.nombre AS nombreCategoria " +
                    "FROM servicios " +
                    "JOIN categorias ON servicios.idCategoria = categorias.id " +
                    "WHERE servicios.idCategoria = ?"; // Para filtrar por estado: AND servicios.estado = true
            sentencia = conexionABaseDeDatos.getConnection().prepareStatement(consulta);
            sentencia.setInt(1, idCategoria);

            ResultSet res = sentencia.executeQuery();
            while (res.next()) {
                ServicioEntity objServicio = mapearResultSetAServicio(res);
                servicios.add(objServicio);
            }

            sentencia.close();
            conexionABaseDeDatos.desconectar();

        } catch (SQLException e) {
            System.out.println("Error en la consulta por categoría: " + e.getMessage());
        }

        return servicios.isEmpty() ? Optional.empty() : Optional.of(servicios);
    }

    private ServicioEntity mapearResultSetAServicio(ResultSet res) throws SQLException {
        ServicioEntity objServicio = new ServicioEntity();
        objServicio.setId(res.getInt("id"));
        objServicio.setNombre(res.getString("nombre"));
        objServicio.setDescripcion(res.getString("descripcion"));
        objServicio.setPrecio(res.getBigDecimal("precio"));
        objServicio.setDuracion(res.getInt("duracion"));
        objServicio.setImagen(res.getString("imagen"));
        objServicio.setEstado(res.getBoolean("estado"));
        objServicio.setObjCategoria(
                new CategoriaEntity(res.getInt("idCategoria"), res.getString("nombreCategoria")));
        return objServicio;
    }
}