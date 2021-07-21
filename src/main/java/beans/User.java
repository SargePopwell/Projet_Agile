package beans;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

public class User implements Crud{

	private int idUser;
	private String surname;
	private String name;
	private String email;
	private String password;
	private String phone;
	private String adress;
	private String gender;
	private int idAdmin;
	private int idFormation;
	private boolean intern;
	
	private ArrayList<HalfDay> halfdays;
	
	public User() {
		
	}
	
	public User (Boolean isIntern) {
		this.intern = isIntern;
	}
	
	public User(String surname, String name, String email, String password, String phone, String adress,
			String gender, int idAdmin, boolean intern) {
		super();
		
		this.surname = surname;
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.adress = adress;
		setGender(gender);
		this.idAdmin = idAdmin;
		this.intern = intern;
	}
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
		if (gender.equals("homme"))
		this.gender = "M";
		else if (gender.equals("femme"))
			this.gender = "F";
		else
			this.gender = "O";
	}
	
	public int getIdAdmin() {
		return idAdmin;
	}
	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}
	
	public boolean isIntern() {
		return intern;
	}
	public void setIntern(boolean intern) {
		this.intern = intern;
	}
	
	public int getIdFormation() {
		return idFormation;
	}

	public void setIdFormation(int idFormation) {
		this.idFormation = idFormation;
	}
	
	public ArrayList<HalfDay> getHalfdays() {
		return halfdays;
	}

	public void setHalfdays(ArrayList<HalfDay> halfdays) {
		this.halfdays = halfdays;
	}

	public void insert() { // autres crud  à verif car id_formation!!
	//	String query = "INSERT INTO `former`( `surname`, `name`, `email`, `password`, `phone`, `adress`, `gender`, `id_admin`) VALUES ('oh','j','kk','1','1010101010','454','O',1)"; 
		String table = this.isIntern() ? "intern":"former";
		String admin = this.isIntern() ? ",id_formation":",id_admin ";
		String query = "INSERT INTO " + table + " ("
				+ "surname, name, email, password, phone, adress, gender " + admin + " )"
				+ " VALUES (?,?,?,?,?,?,?,?);";
		try (PreparedStatement p = DbConnect.getConnector().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
			
			
			p.setString(1, getSurname());
			p.setString(2, getName());
			p.setString(3, getEmail());
			String salt = BCrypt.gensalt();
			String mdpHashed = BCrypt.hashpw(getPassword(), salt);
			p.setString(4, mdpHashed);
			p.setString(5, getPhone());
			p.setString(6, getAdress());
			p.setString(7, getGender());
			if (!isIntern())
			p.setInt(8, getIdAdmin());
			else
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
		String table = this.isIntern() ? "intern":"former";
		String user = this.isIntern() ? "id_intern":"id_former";
		String admin = this.isIntern() ? ",id_formation":",id_admin ";
		String query = "select " + user + ", surname, name, email, password, phone, adress, gender " + admin
				+ " from " + table + " ;";
		ArrayList<User> users = new ArrayList<>();
		System.out.println(query);
		try (PreparedStatement p = DbConnect.getConnector().prepareStatement(query)){
			
			ResultSet result = p.executeQuery();
			while (result.next()) {
				User u = new User();
				u.setIdUser(result.getInt(user));
				u.setSurname(result.getString("surname"));
				u.setName(result.getString("name"));
				u.setEmail(result.getString("email"));
				u.setPassword(result.getString("password"));
				u.setPhone(result.getString("phone"));
				u.setAdress(result.getString("adress"));
				u.setGender(result.getString("gender"));
				if (!isIntern())
				u.setIdAdmin(result.getInt("id_admin"));
				else
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
	
	public ArrayList<User> selectAllInternsByFormation() {
		
		String query = "select id_intern, surname, u.name, email, password, phone, adress, gender, u.id_formation"
				+ " from intern u, formation f WHERE u.id_formation = f.id_formation AND f.id_formation = ? ;";
		ArrayList<User> users = new ArrayList<>();
		System.out.println(query);
		try (PreparedStatement p = DbConnect.getConnector().prepareStatement(query)){
			
			p.setInt(1, getIdFormation());
			
			ResultSet result = p.executeQuery();
			while (result.next()) {
				User u = new User();
				u.setIdUser(result.getInt("id_intern"));
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
		String table = this.isIntern() ? "intern":"former";
		String user = this.isIntern() ? "id_intern":"id_former";
		String admin = this.isIntern() ? "":",id_admin ";
		String query = "select surname, name, email, password, phone, adress, gender " + admin
				+ "from " + table + " where " + user + " = ?;";
		try (PreparedStatement p = DbConnect.getConnector().prepareStatement(query)){
			p.setInt(1,getIdUser());
			ResultSet result = p.executeQuery();
			
			while (result.next()) {
				this.setSurname(result.getString("surname"));
				this.setName(result.getString("name"));
				this.setEmail(result.getString("email"));
				this.setPassword(result.getString("password"));
				this.setPhone(result.getString("phone"));
				this.setAdress(result.getString("adress"));
				this.setGender(result.getString("gender"));
				this.setIdAdmin(result.getInt("id_admin"));
			
			
			DbConnect.getConnector().close();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	
	@Override
	public void update() { // DANGER
		String table = this.isIntern() ? "intern":"former";
		String user = this.isIntern() ? "id_intern":"id_former";
		String admin = this.isIntern() ? "":",id_admin = ? ";
		String query = "update " + table
				+ " set `surname` = ?, `name` = ?, `email` = ?, `password` = ?,"
				+ " `phone` = ?, `adress` = ?, `gender` = ? " + admin
				+ " where " + user + " = ?";
		try (PreparedStatement p = DbConnect.getConnector().prepareStatement(query)){
			
			p.setString(1, getSurname());
			p.setString(2, getName());
			p.setString(3, getEmail());
			p.setString(4, getPassword());
			p.setString(5, getPhone());
			p.setString(6, getAdress());
			p.setString(7, getGender());
			if (isIntern()) {
			p.setInt(8, getIdAdmin());
			p.setInt(9, getIdUser());
			}
			else {
				p.setInt(8, getIdUser());
			}
			
			p.executeUpdate();
			
			DbConnect.getConnector().close();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void delete() {
		String table = this.isIntern() ? "intern":"former";
		String user = this.isIntern() ? "id_intern":"id_former";
		String query = "delete from " + table
				+ " where " + user + " = ?;";
		try (PreparedStatement p = DbConnect.getConnector().prepareStatement(query)){
			p.setInt(1, getIdUser());
			p.executeUpdate();
			
			DbConnect.getConnector().close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
}