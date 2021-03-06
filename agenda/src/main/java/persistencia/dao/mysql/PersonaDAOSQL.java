package persistencia.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PersonaDAO;
import dto.Domicilio;
import dto.PersonaDTO;

public class PersonaDAOSQL implements PersonaDAO
{
	private static final String insert = "INSERT INTO personas VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM personas WHERE idPersona = ?";
	private static final String readall = "SELECT * FROM personas";
	private static final String update = "UPDATE personas SET Nombre = ?, Telefono = ?, calle = ?, altura = ?, piso = ?, departamento = ?, email = ?, fechaCumpleanios = ?, tipoContacto = ? ,signoZodiaco = ?, pais= ? , provincia= ? , localidad= ? WHERE idPersona = ?";

	public boolean insert(PersonaDTO persona) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);

			statement.setInt(1, persona.getIdPersona());
			statement.setString(2, persona.getNombre());
			statement.setString(3, persona.getTelefono());
			statement.setString(4, persona.getDomicilio().getCalle());
			statement.setString(5, persona.getDomicilio().getAltura());
			statement.setString(6, persona.getDomicilio().getPiso());
			statement.setString(7, persona.getDomicilio().getDepartamento());
			statement.setString(8, persona.getEmail());
			statement.setDate(9, persona.getFechaDeCumpleanios());
			statement.setString(10, persona.getTipoDeContacto());
			statement.setString(11, persona.getSignoZodiaco());
			statement.setString(12, persona.getPais());
			statement.setString(13, persona.getProvincia());
			statement.setString(14, persona.getLocalidad());
			
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return isInsertExitoso;
	}

	public boolean delete(PersonaDTO persona_a_eliminar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isdeleteExitoso = false;
		try {
			statement = conexion.prepareStatement(delete);
			statement.setString(1, Integer.toString(persona_a_eliminar.getIdPersona()));
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isdeleteExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isdeleteExitoso;
	}

	public List<PersonaDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				personas.add(getPersonaDTO(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personas;
	}

	private PersonaDTO getPersonaDTO(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("idPersona");
		String nombre = resultSet.getString("Nombre");
		String tel = resultSet.getString("Telefono");
		String calle = resultSet.getString("calle");
		String altura = resultSet.getString("altura");
		String piso = resultSet.getString("piso");
		String departamento = resultSet.getString("departamento");
		String email = resultSet.getString("email");
		Date fechaCumpleanios = resultSet.getDate("fechaCumpleanios");
		Domicilio domicilio = new Domicilio(calle, altura, piso, departamento);
		String etiqueta = resultSet.getString("tipoContacto");
		String signoZodiaco = resultSet.getString("signoZodiaco");
		String Pais = resultSet.getString("pais");
		String Provincia = resultSet.getString("provincia");
		String Localidad = resultSet.getString("localidad");
		
		return new PersonaDTO(id, nombre, tel, domicilio, email, fechaCumpleanios, etiqueta, signoZodiaco, Pais, Provincia, Localidad);
	}

	
	public boolean updatePersona(int idPersona, PersonaDTO nuevosDatos) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try {
			statement = conexion.prepareStatement(update);

			statement.setString(1, nuevosDatos.getNombre());
			statement.setString(2, nuevosDatos.getTelefono());
			statement.setString(3, nuevosDatos.getDomicilio().getCalle());
			statement.setString(4, nuevosDatos.getDomicilio().getAltura());
			statement.setString(5, nuevosDatos.getDomicilio().getPiso());
			statement.setString(6, nuevosDatos.getDomicilio().getDepartamento());
			statement.setString(7, nuevosDatos.getEmail());
			statement.setDate(8, nuevosDatos.getFechaDeCumpleanios());
			statement.setString(9, nuevosDatos.getTipoDeContacto());
			statement.setString(10, nuevosDatos.getSignoZodiaco());
			statement.setString(11, nuevosDatos.getPais());
			statement.setString(12, nuevosDatos.getProvincia());
			statement.setString(13, nuevosDatos.getLocalidad());
			statement.setInt(14, idPersona);

			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isUpdateExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isUpdateExitoso;
	}
}
