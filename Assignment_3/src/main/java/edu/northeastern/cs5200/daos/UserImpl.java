package edu.northeastern.cs5200.daos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.northeastern.cs5200.Connection;
import edu.northeastern.cs5200.models.User;

public class UserImpl implements UserDao {
	static final String CREATE_PERSON = "INSERT INTO person (id, username,password, firstname, lastname, email) VALUES (?, ?, ?, ?, ?, ?)";
	static final String CREATE_USER = "INSERT INTO user (id, person) VALUES (?,?)" ;
	@Override
	public void createUser(User user) {
		try {
			java.sql.Connection conn = Connection.getConnection();
			PreparedStatement prepStatement = conn.prepareStatement(CREATE_PERSON);
			prepStatement.setInt(1, user.getId());
			prepStatement.setString(2, user.getUsername());
			prepStatement.setString(3, user.getPassword());
			prepStatement.setString(4, user.getFirstname());
			prepStatement.setString(5, user.getLastname());
			prepStatement.setString(6, user.getEmail());

			prepStatement.executeUpdate();
			
			prepStatement = conn.prepareStatement(CREATE_USER);
			prepStatement.setInt(1, user.getId());
			prepStatement.setInt(2, user.getId());
			
			prepStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
