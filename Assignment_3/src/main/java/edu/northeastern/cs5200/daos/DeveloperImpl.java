package edu.northeastern.cs5200.daos;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import edu.northeastern.cs5200.Connection;
import edu.northeastern.cs5200.models.Address;
import edu.northeastern.cs5200.models.Developer;
import edu.northeastern.cs5200.models.Phone;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DeveloperImpl implements DeveloperDao {
	static final String CREATE_PERSON = "INSERT INTO person (id, username,password, firstname, lastname, email) VALUES (?, ?, ?, ?, ?, ?)";
	static final String CREATE_DEVELOPER = "INSERT INTO developer (id, person, developer_key) VALUES (?,?,?)" ;
	static final String CREATE_ADDRESS = "INSERT INTO address (street1, street2, city, state, zip, primary, person) VALUES (?, ?, ?, ?, ?, ?, ?)";
	static final String CREATE_PHONE = "INSERT INTO phone (phone, `primary`, person) VALUES (?,?,?)";
	static final String FIND_ALL_DEVELOPER = "SELECT * FROM developer INNER JOIN person ON developer.id = person.id";
	static final String FIND_DEVELOPER_BY_ID = "SELECT * FROM developer INNER JOIN person ON developer.id = person.id WHERE developer.id = ?";
	static final String FIND_DEVELOPER_BY_USERNAME = "SELECT * FROM developer INNER JOIN person ON developer.id = person.id WHERE person.username = ?";
	static final String FIND_DEVELOPER_BY_CREDENTIALS = "SELECT * FROM developer INNER JOIN person ON developer.id = person.id WHERE person.username = ? AND person.password = ?";
	static final String UPDATE_PERSON = "UPDATE person SET id = ?, username = ?, password = ?, firstname = ?, lastname = ?, email = ? WHERE id = ?";
	static final String UPDATE_DEVELOPER = "UPDATE developer SET id = ?, person = ?, developer_key = ? WHERE id = ?";
	static final String UPDATE_ADDRESS = "UPDATE address SET id = ?, street1 = ?, street2 = ?, city = ?, state = ?, zip = ?, `primary` = ?, person = ? WHERE person = ?";
	static final String UPDATE_PHONE = "INSERT INTO phone id = ?, phone = ?, `primary` = ?, person = ?  WHERE person = ?";
	static final String DELETE_DEVELOPER = "DELETE FROM developer WHERE developer.id = ?";
	static final String DELETE_PERSON = "DELETE FROM person WHERE id = ?";
	static final String DELETE_ADDRESS = "DELETE FROM address WHERE person = ?";
	static final String DELETE_PHONE = "DELETE FROM phone WHERE person = ?";
	
	@Override
	public void createDeveloper(Developer developer) {
		// TODO Auto-generated method stub
		try {
			java.sql.Connection conn = Connection.getConnection();
			PreparedStatement prepStatement = conn.prepareStatement(CREATE_PERSON);
			prepStatement.setInt(1, developer.getId());
			prepStatement.setString(2, developer.getUsername());
			prepStatement.setString(3, developer.getPassword());
			prepStatement.setString(4, developer.getFirstname());
			prepStatement.setString(5, developer.getLastname());
			prepStatement.setString(6, developer.getEmail());

			prepStatement.executeUpdate();
			
			prepStatement = conn.prepareStatement(CREATE_DEVELOPER);
			prepStatement.setInt(1, developer.getId());
			prepStatement.setInt(2, developer.getId());
			prepStatement.setString(3, developer.getDeveloperKey());
			
			prepStatement.executeUpdate();
			
			if(developer.getAddresses() != null) {
				for(Address ad : developer.getAddresses()) {
					prepStatement = conn.prepareStatement(CREATE_ADDRESS);
					//prepStatement.setInt(1, ad.getId());
					prepStatement.setString(1, ad.getStreet1());
					prepStatement.setString(2, ad.getStreet2());
					prepStatement.setString(3,ad.getCity());
					prepStatement.setString(4,ad.getState());
					prepStatement.setString(5,ad.getZip());
					prepStatement.setInt(6,ad.getPrimary());
					prepStatement.setInt(7,ad.getPerson());
					prepStatement.executeUpdate();
				}
			}
			
			if(developer.getPhones() != null) {
				for(Phone p:developer.getPhones()) {
					prepStatement = conn.prepareStatement(CREATE_PHONE);
					//prepStatement.setInt(1, p.getId());
					prepStatement.setString(1, p.getPhone());
					prepStatement.setInt(2, p.getPrimary());
					prepStatement.setInt(3,p.getPerson());
					prepStatement.executeUpdate();
				}
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Collection<Developer> findAllDevelopers() {
		// TODO Auto-generated method stub
		ArrayList<Developer> list = new ArrayList<>();
		try {
			java.sql.Connection conn = Connection.getConnection();
			PreparedStatement prepStatement = conn.prepareStatement(FIND_ALL_DEVELOPER);

			ResultSet res = prepStatement.executeQuery();
			
			while(res.next()) {
				Developer developer = new Developer(res.getString("developer_key"), res.getInt("id"), 
						res.getString("firstname"), res.getString("lastname"), res.getString("username"), 
						res.getString("password"), res.getString("email"), res.getDate("dob"));
				list.add(developer);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Developer findDeveloperById(int developerId) {
		// TODO Auto-generated method stub
		Developer developer = null;
		try {
			java.sql.Connection conn = Connection.getConnection();
			PreparedStatement prepStatement = conn.prepareStatement(FIND_DEVELOPER_BY_ID);
			prepStatement.setInt(1, developerId);
			
			ResultSet res = prepStatement.executeQuery();
			
			developer = new Developer(res.getString("developer_key"), res.getInt("id"), 
					res.getString("firstname"), res.getString("lastname"), res.getString("username"), 
					res.getString("password"), res.getString("email"), res.getDate("dob"));
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return developer;
	}

	@Override
	public Developer findDeveloperByUsername(String username) {
		// TODO Auto-generated method stub
		Developer developer = null;
		try {
			java.sql.Connection conn = Connection.getConnection();
			PreparedStatement prepStatement = conn.prepareStatement(FIND_DEVELOPER_BY_USERNAME);
			prepStatement.setString(1, username);
			
			ResultSet res = prepStatement.executeQuery();
			
			developer = new Developer(res.getString("developer_key"), res.getInt("id"), 
					res.getString("firstname"), res.getString("lastname"), res.getString("username"), 
					res.getString("password"), res.getString("email"), res.getDate("dob"));
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return developer;
	}

	@Override
	public Developer findDeveloperByCredentials(String username, String password) {
		// TODO Auto-generated method stub
		Developer developer = null;
		try {
			java.sql.Connection conn = Connection.getConnection();
			PreparedStatement prepStatement = conn.prepareStatement(FIND_DEVELOPER_BY_CREDENTIALS);
			prepStatement.setString(1, username);
			prepStatement.setString(2, password);
			
			ResultSet res = prepStatement.executeQuery();
			
			developer = new Developer(res.getString("developer_key"), res.getInt("id"), 
					res.getString("firstname"), res.getString("lastname"), res.getString("username"), 
					res.getString("password"), res.getString("email"), res.getDate("dob"));
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return developer;
	}

	@Override
	public int updateDeveloper(int developerId, Developer developer) {
		// TODO Auto-generated method stub
		int res = 0;
		try {
			java.sql.Connection conn = Connection.getConnection();
			PreparedStatement prepStatement = conn.prepareStatement(UPDATE_PERSON);
			prepStatement.setInt(1, developerId);
			prepStatement.setString(2, developer.getUsername());
			prepStatement.setString(3, developer.getPassword());
			prepStatement.setString(4, developer.getFirstname());
			prepStatement.setString(5, developer.getLastname());
			prepStatement.setString(6, developer.getEmail());
			prepStatement.setInt(7, developerId);

			prepStatement.executeUpdate();
			
			prepStatement = conn.prepareStatement(UPDATE_DEVELOPER);
			prepStatement.setInt(1, developerId);
			prepStatement.setInt(2, developer.getId());
			prepStatement.setString(3, developer.getDeveloperKey());
			prepStatement.setInt(4, developerId);
			
			res = prepStatement.executeUpdate();
			
			prepStatement = conn.prepareStatement(DELETE_ADDRESS);
			prepStatement.setInt(1, developer.getId());
			prepStatement.executeUpdate();
			
			prepStatement = conn.prepareStatement(DELETE_PHONE);
			prepStatement.setInt(1, developer.getId());
			prepStatement.executeUpdate();
			
			if(developer.getAddresses() != null) {
				for(Address ad : developer.getAddresses()) {
					prepStatement = conn.prepareStatement(CREATE_ADDRESS);
					//prepStatement.setInt(1, ad.getId());
					prepStatement.setString(1, ad.getStreet1());
					prepStatement.setString(2, ad.getStreet2());
					prepStatement.setString(3,ad.getCity());
					prepStatement.setString(4,ad.getState());
					prepStatement.setString(5,ad.getZip());
					prepStatement.setInt(6,ad.getPrimary());
					prepStatement.setInt(7,ad.getPerson());
					prepStatement.executeUpdate();
				}
			}
			
			if(developer.getPhones() != null) {
				for(Phone p:developer.getPhones()) {
					prepStatement = conn.prepareStatement(CREATE_PHONE);
					//prepStatement.setInt(1, p.getId());
					prepStatement.setString(1, p.getPhone());
					prepStatement.setInt(2, p.getPrimary());
					prepStatement.setInt(3,p.getPerson());
					prepStatement.executeUpdate();
				}
			}

			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int deleteDeveloper(int developerId) {
		// TODO Auto-generated method stub
		int res = 0;
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement prepStatement = conn.prepareStatement(DELETE_DEVELOPER);
			prepStatement.setInt(1, developerId);
			prepStatement.executeUpdate();
			
			prepStatement = conn.prepareStatement(DELETE_PERSON);
			prepStatement.setInt(1, developerId);
			res = prepStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

}
