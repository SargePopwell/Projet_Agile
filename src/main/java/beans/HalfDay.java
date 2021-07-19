package beans;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HalfDay implements Crud{

	private int idHd;
	private Date date;
	private boolean morning;
	private int idUser;
	
	public int getIdHd() {
		return idHd;
	}
	public void setIdHd(int idHd) {
		this.idHd = idHd;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public boolean isMorning() {
		return morning;
	}
	public void setMorning(boolean morning) {
		this.morning = morning;
	}
	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	@Override
	public void insert() {
		String query = "INSERT INTO half_day("
				+  "date, morning, id_user)"
				+ " VALUES (?,?,?)";
		try (PreparedStatement p = DbConnect.getConnector().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
			p.setDate(1, getDate());
			p.setBoolean(2, isMorning());
			p.setInt(3,  getIdUser());
			
			p.executeUpdate();
			
			ResultSet result = p.getGeneratedKeys();
			while (result.next())
				setIdHd(result.getInt(1));
			
			DbConnect.getConnector().close();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<?> selectAll() {
		String query = "SELECT 'id_hd', `date`, `morning`"
				+ " FROM `half_day`";
		ArrayList<HalfDay> halfdays = new ArrayList<>();
		try (PreparedStatement p = DbConnect.getConnector().prepareStatement(query)){
			
			ResultSet result = p.executeQuery(query);
			while (result.next()) {
				HalfDay d = new HalfDay();
				d.setIdHd(result.getInt("id_hd"));
				d.setDate(result.getDate("date"));
				d.setMorning(result.getBoolean("morning"));
				
				halfdays.add(d);
			}
			DbConnect.getConnector().close();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return halfdays;
	}
	
	@Override
	public HalfDay select() {
		String query = "SELECT 'id_hd', `date`, `morning`"
				+ " FROM `half_day` where id_hd = ?";
		
		try (PreparedStatement p = DbConnect.getConnector().prepareStatement(query)){
			p.setInt(1,  getIdHd());
			ResultSet result = p.executeQuery(query);
			while (result.next()) {
				
				this.setIdHd(result.getInt("id_hd"));
				this.setDate(result.getDate("date"));
				this.setMorning(result.getBoolean("morning"));
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
		String query = "update `half_day`"
				+ "set `date` = ?, `morning` = ?"
				+ " where id_hd= ?";
		try (PreparedStatement p = DbConnect.getConnector().prepareStatement(query)){
			p.setDate(1, getDate());
			p.setBoolean(2, isMorning());
			p.setInt(3, getIdHd());
			p.executeUpdate();
			
			DbConnect.getConnector().close();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void delete() {
		String query = "delete from `half_day`"
				+ " where id_hd = ?";
		try (PreparedStatement p = DbConnect.getConnector().prepareStatement(query)){
			
			p.setInt(1, getIdHd());
			
			p.executeUpdate();
			
			DbConnect.getConnector().close();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}