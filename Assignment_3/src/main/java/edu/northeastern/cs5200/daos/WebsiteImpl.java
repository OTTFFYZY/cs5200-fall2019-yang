package edu.northeastern.cs5200.daos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import edu.northeastern.cs5200.Connection;
import edu.northeastern.cs5200.models.Website;
import edu.northeastern.cs5200.models.Widget;

public class WebsiteImpl implements WebsiteDao {
	static final String CREATE_WEBSITE = "INSERT INTO website (id,name, description, created, updated, visits) VALUES(?, ?, ?, ?, ?, ?)";
	static final String FIND_ALL_WEBSITES = "SELECT * FROM website";
	static final String FIND_WEBSITES_FOR_DEVELOPER = "SELECT * FROM website_role INNER JOIN website ON website.id = website_role.website WHERE website_role.role = \"owner\" AND website_role.developer = ?";
	static final String FIND_WEBSITE_BY_ID = "SELECT * FROM website WHERE id = ?";
	static final String UPDATE_WEBSITE = "UPDATE website SET id = ?, name = ?, description = ?, created = ?, updated = ?, visits = ? WHERE id = ?";
	static final String DELETE_WEBSITE = "DELETE FROM website WHERE id = ?";
	@Override
	public void createWebsiteForDeveloper(int developerId, Website website) {
		// TODO Auto-generated method stub
		java.sql.Connection conn;
		try {
			conn = Connection.getConnection();
			PreparedStatement prepStatement = conn.prepareStatement(CREATE_WEBSITE);
			
			prepStatement.setInt(1, website.getId());
			prepStatement.setString(2, website.getName());
			prepStatement.setString(3, website.getDescription());
			prepStatement.setDate(4, (Date) website.getCreated());
			prepStatement.setDate(5, (Date) website.getUpdated());
			prepStatement.setInt(6, website.getVisits());
			
			prepStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Collection<Website> findAllWebsites() {
		// TODO Auto-generated method stub
		ArrayList<Website> list = new ArrayList<>();
		try {
			java.sql.Connection conn = Connection.getConnection();
			PreparedStatement prepStatement = conn.prepareStatement(FIND_ALL_WEBSITES);

			ResultSet res = prepStatement.executeQuery();
			//int id, String name, String description, Date created, Date updated, int visits
			while(res.next()) {
				Website website = new Website(res.getInt("id"), res.getString("name"), 
						res.getString("description"), res.getDate("created"), 
						res.getDate("updated"),  res.getInt("visits"));
				list.add(website);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}

	@Override
	public Collection<Website> findWebsitesForDeveloper(int developerId) {
		// TODO Auto-generated method stub
		ArrayList<Website> list = new ArrayList<>();
		try {
			java.sql.Connection conn = Connection.getConnection();
			PreparedStatement prepStatement = conn.prepareStatement(FIND_WEBSITES_FOR_DEVELOPER);

			ResultSet res = prepStatement.executeQuery();
			//int id, String name, String description, Date created, Date updated, int visits
			while(res.next()) {
				Website website = new Website(res.getInt("id"), res.getString("name"), 
						res.getString("description"), res.getDate("created"), 
						res.getDate("updated"),  res.getInt("visits"));
				list.add(website);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}

	@Override
	public Website findWebsiteById(int websiteId) {
		// TODO Auto-generated method stub
		Website website = null;
		try {
			java.sql.Connection conn = Connection.getConnection();
			PreparedStatement prepStatement = conn.prepareStatement(FIND_WEBSITE_BY_ID);
			prepStatement.setInt(1, websiteId);
			
			ResultSet res = prepStatement.executeQuery();
			
			website = new Website(res.getInt("id"), res.getString("name"), 
					res.getString("description"), res.getDate("created"), 
					res.getDate("updated"),  res.getInt("visits"));
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return website;
	}

	@Override
	public int updateWebsite(int websiteId, Website website) {
		// TODO Auto-generated method stub
		// UPDATE_WEBSITE = "UPDATE website SET id = ?, name = ?, description = ?, created = ?, updated = ?, visits = ? WHERE id = ?";
		
		int res = 0;
		try {
			java.sql.Connection conn = Connection.getConnection();
			PreparedStatement prepStatement = conn.prepareStatement(UPDATE_WEBSITE);
			
			prepStatement.setInt(1, website.getId());
			prepStatement.setString(2, website.getName());
			prepStatement.setString(3, website.getDescription());
			prepStatement.setDate(4, website.getCreated());
			prepStatement.setDate(5, website.getUpdated());
			prepStatement.setInt(6, website.getVisits());
			prepStatement.setInt(7, websiteId);
			
			res = prepStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int deleteWebsite(int websiteId) {
		// TODO Auto-generated method stub
		//static final String DELETE_WEBSITE = "DELETE FROM website WHERE id = ?";
		int res = 0;
		try {
			java.sql.Connection conn = Connection.getConnection();
			PreparedStatement prepStatement = conn.prepareStatement(DELETE_WEBSITE);
			
			prepStatement.setInt(1, websiteId);
			
			res = prepStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

}
