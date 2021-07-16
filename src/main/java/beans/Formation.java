package beans;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Formation implements Crud{

	private int idFormation;
	private String name;
	private Date dateStart;
	private Date dateEnd;
	private String description;
	private int idDay;
	private int idUser;
	
	public int getIdFormation() {
		return idFormation;
	}
	public void setIdFormation(int idFormation) {
		this.idFormation = idFormation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDateStart() {
		return dateStart;
	}
	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}
	public Date getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getIdDay() {
		return idDay;
	}
	public void setIdDay(int idDay) {
		this.idDay = idDay;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	@Override
	public void insert() {
		String query = "INSERT INTO `formation`("
				+  "'name`, `date_start`, `date_end`, `description`, `id_day`, `id_user`)"
				+ " VALUES (?,?,?,?,?,?)";
		try (PreparedStatement p = DbConnect.getConnector().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
			p.setString(1, getName());
			p.setDate(2, getDateStart());
			p.setDate(3, getDateEnd());
			p.setString(4, getDescription());
			p.setInt(5, getIdDay());
			p.setInt(6, getIdUser());
			
			p.executeUpdate();
			
			ResultSet result = p.getGeneratedKeys();
			while (result.next())
				setIdFormation(result.getInt(1));
			
			DbConnect.getConnector().close();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<?> selectAll() {
		String query = "SELECT 'id_formation', `name`, `date_start`, `date_end`, `description`, `id_day`, `id_user`"
				+ " FROM `formation`";
		ArrayList<Formation> formations = new ArrayList<>();
		try (PreparedStatement p = DbConnect.getConnector().prepareStatement(query)){
			
			ResultSet result = p.executeQuery(query);
			while (result.next()) {
				Formation f = new Formation();
				f.setIdFormation(result.getInt("id_formation"));
				f.setName(result.getString("name"));
				f.setDateStart(result.getDate("date_start"));
				f.setDateEnd(result.getDate("date_end"));
				f.setDescription(result.getString("description"));
				f.setIdDay(result.getInt("id_day"));
				f.setIdUser(result.getInt("id_user"));
				
				formations.add(f);
			}
				
			
			DbConnect.getConnector().close();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return formations;
	}
	
	@Override
	public Formation select() {
		String query = "SELECT 'id_formation', `name`, `date_start`, `date_end`, `description`, `id_day`, `id_user`"
				+ " FROM `formation` where id_formation = ?";
		
		try (PreparedStatement p = DbConnect.getConnector().prepareStatement(query)){
			p.setInt(1,  getIdFormation());
			ResultSet result = p.executeQuery(query);
			while (result.next()) {
				
				
				this.setName(result.getString("name"));
				this.setDateStart(result.getDate("date_start"));
				this.setDateEnd(result.getDate("date_end"));
				this.setDescription(result.getString("description"));
				this.setIdDay(result.getInt("id_day"));
				this.setIdUser(result.getInt("id_user"));
				
				
			}
				
			DbConnect.getConnector().close();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	@Override
	public void update() {
		String query = "update `formation`"
				+ "set `name` = ?, `date_start` = ?, `date_end` = ?, `description` = ?, `id_day` = ?, `id_user` = ?"				+ " `id_formation` = ?"
				+ " where id_formation = ?";
		try (PreparedStatement p = DbConnect.getConnector().prepareStatement(query)){
			p.setString(1, getName());
			p.setDate(2, getDateStart());
			p.setDate(3, getDateEnd());
			p.setString(4, getDescription());
			p.setInt(5, getIdDay());
			p.setInt(6, getIdUser());
			p.setInt(7, getIdFormation());
			
			
			p.executeUpdate();
			
			DbConnect.getConnector().close();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void delete() {
		String query = "delete from `formation`"
				+ " where id_formation = ?";
		try (PreparedStatement p = DbConnect.getConnector().prepareStatement(query)){
			
			p.setInt(1, getIdFormation());
			
			p.executeUpdate();
			
			DbConnect.getConnector().close();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}