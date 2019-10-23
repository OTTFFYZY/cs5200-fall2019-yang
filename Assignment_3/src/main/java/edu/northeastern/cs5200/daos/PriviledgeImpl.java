package edu.northeastern.cs5200.daos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.northeastern.cs5200.Connection;

public class PriviledgeImpl implements PriviledgeDao {
	static final String ASSIGN_PAGE_PRIVILEGE="INSERT INTO page_privilege (privilege, developer, page) VALUES(?,?,?)";
	static final String ASSIGN_WEBSITE_PRIVILEGE="INSERT INTO website_privilege (privilege, developer, website) VALUES(?,?,?)";
	static final String DELETE_PAGE_PRIVILEGE="DELETE FROM page_privilege WHERE privilege = ?, developer = ?, website = ?";
	static final String DELETE_WEBSITE_PRIVILEGE="DELETE FROM website_privilege WHERE privilege = ?, developer = ?, website = ?";
	
	@Override
	public void assignWebsitePrivilege(int developerId, int websiteId, String priviledge) {
		// TODO Auto-generated method stub
		java.sql.Connection conn;
		try {
			conn = Connection.getConnection();
			PreparedStatement prepStatement = conn.prepareStatement(ASSIGN_WEBSITE_PRIVILEGE);
			
			prepStatement.setString(1, priviledge);
			prepStatement.setInt(2, developerId);
			prepStatement.setInt(3, websiteId);
						
			prepStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void assignPagePriviledge(int developerId, int pageId, String priviledge) {
		// TODO Auto-generated method stub
		java.sql.Connection conn;
		try {
			conn = Connection.getConnection();
			PreparedStatement prepStatement = conn.prepareStatement(ASSIGN_PAGE_PRIVILEGE);
			
			prepStatement.setString(1, priviledge);
			prepStatement.setInt(2, developerId);
			prepStatement.setInt(3, pageId);
						
			prepStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteWebsitePriviledge(int developerId, int websiteId, String priviledge) {
		// TODO Auto-generated method stub
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement prepStatement = conn.prepareStatement(DELETE_PAGE_PRIVILEGE);
			prepStatement.setString(1, priviledge);
			prepStatement.setInt(2, developerId);
			prepStatement.setInt(3, websiteId);
			
			prepStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deletePagePriviledge(int developerId, int pageId, String priviledge) {
		// TODO Auto-generated method stub
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement prepStatement = conn.prepareStatement(DELETE_PAGE_PRIVILEGE);
			prepStatement.setString(1, priviledge);
			prepStatement.setInt(2, developerId);
			prepStatement.setInt(3, pageId);
			
			prepStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
