package edu.northeastern.cs5200.daos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.northeastern.cs5200.Connection;

public class RoleImpl implements RoleDao {
	static final String ASSIGN_PAGE_ROLE = "INSERT INTO page_role (role, developer, `page`) VALUES(?,?,?)";
	static final String ASSIGN_WEBSITE_ROLE = "INSERT INTO website_role (role, developer, website) VALUES(?,?,?)";
	static final String DELETE_PAGE_ROLE = "DELETE FROM page_role WHERE role = ? AND developer = ? AND `page` = ?";
	static final String DELETE_WEBSITE_ROLE = "DELETE FROM website_role WHERE role = ? AND developer = ? AND website = ?";
	static final String DELETE_PAGE_ROLE_BY_PAGE = "DELETE FROM page_role WHERE `page` = ?";
	static final String DELETE_WEBSITE_ROLE_BY_WEBSITE = "DELETE FROM website_role WHERE website = ?";
	
	@Override
	public void assignWebsiteRole(int developerId, int websiteId, String roleId) {
		// TODO Auto-generated method stub
		java.sql.Connection conn;
		try {
			conn = Connection.getConnection();
			PreparedStatement prepStatement = conn.prepareStatement(ASSIGN_WEBSITE_ROLE);
			
			prepStatement.setString(1, roleId);
			prepStatement.setInt(2, developerId);
			prepStatement.setInt(3, websiteId);
						
			prepStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void assignPageRole(int developerId, int pageId, String roleId) {
		// TODO Auto-generated method stub
		java.sql.Connection conn;
		try {
			conn = Connection.getConnection();
			PreparedStatement prepStatement = conn.prepareStatement(ASSIGN_PAGE_ROLE);
			
			prepStatement.setString(1, roleId);
			prepStatement.setInt(2, developerId);
			prepStatement.setInt(3, pageId);
						
			prepStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteWebsiteRole(int developerId, int websiteId, String roleId) {
		// TODO Auto-generated method stub
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement prepStatement = conn.prepareStatement(DELETE_WEBSITE_ROLE);
			prepStatement.setString(1, roleId);
			prepStatement.setInt(2, developerId);
			prepStatement.setInt(3, websiteId);
			
			prepStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deletePageRole(int developerId, int pageId, String roleId) {
		// TODO Auto-generated method stub
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement prepStatement = conn.prepareStatement(DELETE_PAGE_ROLE);
			prepStatement.setString(1, roleId);
			prepStatement.setInt(2, developerId);
			prepStatement.setInt(3, pageId);
			
			prepStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deletePageRoleByPage(int pageId) {
		// TODO Auto-generated method stub
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement prepStatement = conn.prepareStatement(DELETE_PAGE_ROLE_BY_PAGE);
			prepStatement.setInt(1, pageId);
			
			prepStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteWebsiteRoleByWebsite(int websiteId) {
		// TODO Auto-generated method stub
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement prepStatement = conn.prepareStatement(DELETE_WEBSITE_ROLE_BY_WEBSITE);
			prepStatement.setInt(1, websiteId);
			
			prepStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
