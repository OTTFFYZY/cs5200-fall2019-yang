package edu.northeastern.cs5200.daos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import edu.northeastern.cs5200.Connection;
import edu.northeastern.cs5200.models.Developer;
import edu.northeastern.cs5200.models.Page;

public class PageImpl implements PageDao {
	static final String CREATE_PAGE="INSERT INTO page (id, title, description, website, views, created, updated) VALUES(?,?,?,?,?,?,?)";

	static final String FIND_ALL_PAGE = "SELECT * FROM page";
	static final String FIND_PAGE_BY_ID = "SELECT * FROM `page` WHERE id = ?";
	static final String FIND_PAGE_FOR_WEBSITE = "SELECT * FROM page WHERE website = ?";
	static final String UPDATE_PAGE = "UPDATE `page` SET id = ?, title = ?, description = ?, website = ?, views = ?, created = ?, updated = ? WHERE id = ?";
	static final String DELETE_PAGE = "DELETE FROM page WHERE id = ?";
	
	@Override
	public void createPageForWebsite(int websiteId, Page page) {
		// TODO Auto-generated method stub
		java.sql.Connection conn;
		try {
			conn = Connection.getConnection();
			PreparedStatement prepStatement = conn.prepareStatement(CREATE_PAGE);
			
			prepStatement.setInt(1, page.getId());
			prepStatement.setString(2, page.getTitle());
			prepStatement.setString(3, page.getDescription());
			prepStatement.setInt(4, websiteId);
			prepStatement.setInt(5, page.getViews());
			prepStatement.setDate(6, page.getCreated());
			prepStatement.setDate(7, page.getUpdated());
						
			prepStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Collection<Page> findAllPages() {
		// TODO Auto-generated method stub
		ArrayList<Page> list = new ArrayList<>();
		try {
			java.sql.Connection conn = Connection.getConnection();
			PreparedStatement prepStatement = conn.prepareStatement(FIND_ALL_PAGE);

			ResultSet res = prepStatement.executeQuery();
			
			while(res.next()) {
				Page page = new Page(res.getInt("id"), res.getString("title"), 
						res.getString("description"), res.getDate("created"), 
						res.getDate("updated"), res.getInt("views"));
				page.setWebsite(res.getInt("website"));
				list.add(page);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Page findPageById(int pageId) {
		// TODO Auto-generated method stub
		Page page = null;
		try {
			java.sql.Connection conn = Connection.getConnection();
			PreparedStatement prepStatement = conn.prepareStatement(FIND_PAGE_BY_ID);
			prepStatement.setInt(1, pageId);
			
			ResultSet res = prepStatement.executeQuery();
			
			
			page = new Page(res.getInt("id"), res.getString("title"), 
						res.getString("description"), res.getDate("created"), 
						res.getDate("updated"), res.getInt("views"));
			page.setWebsite(res.getInt("website"));
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}

	@Override
	public Collection<Page> findPagesForWebsite(int websiteId) {
		// TODO Auto-generated method stub
		ArrayList<Page> list = new ArrayList<>();
		try {
			java.sql.Connection conn = Connection.getConnection();
			PreparedStatement prepStatement = conn.prepareStatement(FIND_PAGE_FOR_WEBSITE);
			
			//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!  " + websiteId);
			prepStatement.setInt(1, websiteId);
			ResultSet res = prepStatement.executeQuery();
			
			while(res.next()) {
				Page page = new Page(res.getInt("id"), res.getString("title"), 
						res.getString("description"), res.getDate("created"), 
						res.getDate("updated"), res.getInt("views"));
				page.setWebsite(res.getInt("website"));
				list.add(page);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int updatePage(int pageId, Page page) {
		// TODO Auto-generated method stub
		int res = 0;
		try {
			java.sql.Connection conn = Connection.getConnection();
			PreparedStatement prepStatement = conn.prepareStatement(UPDATE_PAGE);
			// id = ?, title = ?, description = ?, website = ?, views = ?, created = ?, updated = ? WHERE id = ?";
			prepStatement.setInt(1, pageId);
			prepStatement.setString(2, page.getTitle());
			prepStatement.setString(3, page.getDescription());
			prepStatement.setInt(4, page.getWebsite());
			prepStatement.setInt(5, page.getViews());
			prepStatement.setDate(6, page.getCreated());
			prepStatement.setDate(7, page.getUpdated());
			prepStatement.setInt(8, pageId);
			
			res = prepStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int deletePage(int pageId) {
		// TODO Auto-generated method stub
		int res = 0;
		try {
			java.sql.Connection conn = Connection.getConnection();
			
			PreparedStatement prepStatement = conn.prepareStatement(DELETE_PAGE);
			prepStatement.setInt(1, pageId);
			res = prepStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

}
