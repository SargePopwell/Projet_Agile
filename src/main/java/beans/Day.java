package beans;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Day implements Crud {

	private int idDay;
	private Date date;
	private String activity;
	private int idFormer;
	private int idFormation;
	
	
	public int getIdDay() {
		return idDay;
	}
	public void setIdDay(int idDay) {
		this.idDay = idDay;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	
	public int getIdFormer() {
		return idFormer;
	}
	public void setIdFormer(int idFormer) {
		this.idFormer = idFormer;
	}
	public int getIdFormation() {
		return idFormation;
	}
	public void setIdFormation(int idFormation) {
		this.idFormation = idFormation;
	}
	
	
	@Override
	public void insert() {
		String query = "INSERT INTO day ("
				+  "date, activity, id_former, id_formation)"
				+ " VALUES (?,?,?,?);";
		try (PreparedStatement p = DbConnect.getConnector().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
			p.setDate(1, getDate());
			p.setString(2, getActivity());
			p.setInt(3, getIdFormer());
			p.setInt(4, getIdFormation());
			
			p.executeUpdate();
			
			ResultSet result = p.getGeneratedKeys();
			while (result.next())
				setIdDay(result.getInt(1));
			
			DbConnect.getConnector().close();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<?> selectAll() {
		String query = "SELECT 'id_day', `date`, `activity`"
				+ " FROM `day`";
		ArrayList<Day> days = new ArrayList<>();
		try (PreparedStatement p = DbConnect.getConnector().prepareStatement(query)){
			
			ResultSet result = p.executeQuery(query);
			while (result.next()) {
				Day d = new Day();
				d.setIdDay(result.getInt("id_day"));
				d.setDate(result.getDate("date"));
				d.setActivity(result.getString("activity"));
				
				days.add(d);
			}
			DbConnect.getConnector().close();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return days;
	}
	
	public ArrayList<Day> selectAllByFormation() {
		String query = "SELECT id_day, date, activity, d.id_formation, id_former"
				+ " FROM day d, formation f WHERE d.id_formation = f.id_formation AND d.id_formation = ?";
		ArrayList<Day> days = new ArrayList<>();
		try (PreparedStatement p = DbConnect.getConnector().prepareStatement(query)){
			
			p.setInt(1, getIdFormation());
			
			ResultSet result = p.executeQuery();
			while (result.next()) {
				Day d = new Day();
				d.setIdDay(result.getInt("id_day"));
				d.setDate(result.getDate("date"));
				d.setActivity(result.getString("activity"));
				d.setIdFormation(result.getInt("id_formation"));
				d.setIdFormer(result.getInt("id_former"));
				
				days.add(d);
			}
			DbConnect.getConnector().close();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return days;
	}
	
	@Override
	public Day select() {
		String query = "SELECT `date`, `activity`"
				+ " FROM `day` where id_day = ?";
		
		try (PreparedStatement p = DbConnect.getConnector().prepareStatement(query)){
			p.setInt(1,  getIdDay());
			ResultSet result = p.executeQuery(query);
			while (result.next()) {
			
				this.setDate(result.getDate("date"));
				this.setActivity(result.getString("activity"));
			
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
		String query = "update `day`"
				+ "set `date` = ?, `activity` = ?"
				+ " where id_day = ?";
		try (PreparedStatement p = DbConnect.getConnector().prepareStatement(query)){
			p.setDate(1, getDate());
			p.setString(2, getActivity());
			p.setInt(3, getIdDay());
			p.executeUpdate();
			
			DbConnect.getConnector().close();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void delete() {
		String query = "delete from `day`"
				+ " where id_day = ?";
		try (PreparedStatement p = DbConnect.getConnector().prepareStatement(query)){
			
			p.setInt(1, getIdDay());
			
			p.executeUpdate();
			
			DbConnect.getConnector().close();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}