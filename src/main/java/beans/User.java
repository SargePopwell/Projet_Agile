package beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class User implements Crud{

	private int idUser;
	private String surname;
	private String name;
	private String email;
	private String password;
	private String phone;
	private String adress;
	private String gender;
	private int idFormation;
	private boolean intern;
	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public int getIdFormation() {
		return idFormation;
	}
	public void setIdFormation(int idFormation) {
		this.idFormation = idFormation;
	}
	
	public boolean isIntern() {
		return intern;
	}
	public void setIntern(boolean intern) {
		this.intern = intern;
	}
	
	@Override
	public void insert() {
		String query = "INSERT INTO " + (isIntern() ? "intern":"former") + "("
				+ "surname, name, email, password, phone, adress, gender, id_formation)"
				+ " VALUES (?,?,?,?,?,?,?,?)";
		try (PreparedStatement p = DbConnect.getConnector().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
			
			p.setString(1, isIntern() ? "intern":"former"); // Créer dans la table intern ou former selon de qui il s'agit
			
			p.setString(1, getSurname());
			p.setString(2, getName());
			p.setString(3, getEmail());
			p.setString(4, getPassword());
			p.setString(5, getPhone());
			p.setString(6, getAdress());
			p.setString(7, getGender());
			p.setInt(8, getIdFormation());
			p.executeUpdate();
			
			ResultSet result = p.getGeneratedKeys();
			while (result.next())
				setIdUser(result.getInt(1));
			
			DbConnect.getConnector().close();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<?> selectAll() {
		String query = "select 'id_user',`surname`, `name`, `email`, `password`, `phone`, `adress`, `gender`, `id_formation`"
				+ "from ?";
		ArrayList<User> users = new ArrayList<>();
		try (PreparedStatement p = DbConnect.getConnector().prepareStatement(query)){
			p.setString(1, isIntern() ? "Intern":"Former");
			ResultSet result = p.executeQuery(query);
			while (result.next()) {
				User u = new User();
				u.setIdUser(result.getInt("id_user"));
				u.setSurname(result.getString("surname"));
				u.setName(result.getString("name"));
				u.setEmail(result.getString("email"));
				u.setPassword(result.getString("password"));
				u.setPhone(result.getString("phone"));
				u.setAdress(result.getString("adress"));
				u.setGender(result.getString("gender"));
				u.setIdFormation(result.getInt("id_formation"));
				u.setIntern(this.isIntern()); // L'user créé est soit intern soit former
				
				users.add(u);
			}
				
			
			DbConnect.getConnector().close();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	@Override
	public User select() {
		String query = "select `surname`, `name`, `email`,`password`, `phone`, `adress`, `gender`, `id_formation`"
				+ "from ? where id_user = ?";
		
		try (PreparedStatement p = DbConnect.getConnector().prepareStatement(query)){
			p.setString(1,  this.isIntern() ? "Intern":"Former");
			p.setInt(2,  getIdUser());
			ResultSet result = p.executeQuery(query);
			while (result.next()) {
				this.setIdUser(result.getInt("id_user"));
				this.setSurname(result.getString("surname"));
				this.setName(result.getString("name"));
				this.setEmail(result.getString("email"));
				this.setEmail(result.getString("email"));
				this.setPhone(result.getString("phone"));
				this.setAdress(result.getString("adress"));
				this.setGender(result.getString("gender"));
				this.setIdFormation(result.getInt("id_formation"));
				
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
		String query = "update `?`"
				+ "set `surname` = ?, `name` = ?, `email` = ?, `password` = ?,"
				+ " `phone` = ?, `adress` = ?, `gender` = ?,`id_formation` = ?"
				+ " where id_user = ?";
		try (PreparedStatement p = DbConnect.getConnector().prepareStatement(query)){
			p.setString(1, this.isIntern()?"Intern":"Former");
			p.setString(2, getSurname());
			p.setString(3, getName());
			p.setString(4, getEmail());
			p.setString(5, getPassword());
			p.setString(6, getPhone());
			p.setString(7, getAdress());
			p.setString(8, getGender());
			
			p.setInt(9, getIdFormation());
			p.setInt(10, getIdUser());
			
			p.executeUpdate();
			
			DbConnect.getConnector().close();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void delete() {
		String query = "delete from `?`"
				+ " where id_user = ?";
		try (PreparedStatement p = DbConnect.getConnector().prepareStatement(query)){
			p.setString(1, this.isIntern() ? "Intern":"Former");
			p.setInt(2, getIdUser());
			
			p.executeUpdate();
			
			DbConnect.getConnector().close();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
}