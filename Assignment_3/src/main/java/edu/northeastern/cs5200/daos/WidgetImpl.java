package edu.northeastern.cs5200.daos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import edu.northeastern.cs5200.Connection;
import edu.northeastern.cs5200.models.Widget;
import edu.northeastern.cs5200.models.ImageWidget;
import edu.northeastern.cs5200.models.HtmlWidget;
import edu.northeastern.cs5200.models.Developer;
import edu.northeastern.cs5200.models.HeadingWidget;
import edu.northeastern.cs5200.models.YouTubeWidget;

public class WidgetImpl implements WidgetDao {
	static final String CREATE_WIDGET = "INSERT INTO widget (`id`, `name`, `dtype`, `text`, `order`, `width`, `height`, `image_src`, `youtube_url`, `page`) VALUES (?,?,?,?,?,?,?,?,?,?)";
	static final String FIND_ALL_WIDGET = "SELECT * FROM widget";
	static final String FIND_WIDGET_BY_ID = "SELECT * FROM widget WHERE id = ?";
	static final String FIND_WIDGET_BY_PAGE = "SELECT * FROM widget WHERE page = ?";
	static final String UPDATE_WIDGET = "UPDATE widget SET `id` = ?, `name` = ?, `dtype` = ?, `text` = ?, `order` = ?, `width` = ?, `height` = ?, `page` = ? WHERE id = ?";
	static final String DELETE_WIDGET = "DELETE FROM widget WHERE id = ?";
	@Override
	public void createWidgetForPage(int pageId, Widget widget) {
		// TODO Auto-generated method stub
		java.sql.Connection conn;
		try {
			conn = Connection.getConnection();
			PreparedStatement prepStatement = conn.prepareStatement(CREATE_WIDGET);
			
			prepStatement.setInt(1, widget.getId());
			prepStatement.setString(2, widget.getName());
			prepStatement.setString(3, widget.getDtype());
			prepStatement.setString(4, widget.getText());
			prepStatement.setInt(5, widget.getOrder());
			prepStatement.setInt(6, widget.getWidth());
			prepStatement.setInt(7, widget.getHeight());
			prepStatement.setInt(10, pageId);
			
			if(widget.getDtype().equals("image")) {
				prepStatement.setString(8, ((ImageWidget) widget).getSrc());
			} else {
				prepStatement.setString(8, null);
			}
			if(widget.getDtype().equals("youtube")) {
				prepStatement.setString(9, ((YouTubeWidget) widget).getUrl());
			} else {
				prepStatement.setString(9, null);
			}
			
			prepStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Collection<Widget> findAllWidgets() {
		// TODO Auto-generated method stub
		ArrayList<Widget> list = new ArrayList<>();
		try {
			java.sql.Connection conn = Connection.getConnection();
			PreparedStatement prepStatement = conn.prepareStatement(FIND_ALL_WIDGET);

			ResultSet res = prepStatement.executeQuery();
			
			while(res.next()) {
				Widget widget = new Widget(res.getInt("id"), res.getString("name"), 
						res.getInt("width"), res.getInt("height"), res.getString("cssClass"), 
						res.getString("cssStyle"), res.getString("text"), res.getInt("order"));
				list.add(widget);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Widget findWidgetById(int widgetId) {
		// TODO Auto-generated method stub
		Widget widget = null;
		try {
			java.sql.Connection conn = Connection.getConnection();
			PreparedStatement prepStatement = conn.prepareStatement(FIND_WIDGET_BY_ID);
			prepStatement.setInt(1, widgetId);
			
			ResultSet res = prepStatement.executeQuery();
			
			widget = new Widget(res.getInt("id"), res.getString("name"), 
						res.getInt("width"), res.getInt("height"), res.getString("cssClass"), 
						res.getString("cssStyle"), res.getString("text"), res.getInt("order"));
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return widget;
	}

	@Override
	public Collection<Widget> findWidgetsForPage(int pageId) {
		// TODO Auto-generated method stub
		ArrayList<Widget> list = new ArrayList<>();
		try {
			java.sql.Connection conn = Connection.getConnection();
			PreparedStatement prepStatement = conn.prepareStatement(FIND_WIDGET_BY_PAGE);
			prepStatement.setInt(1, pageId);

			ResultSet res = prepStatement.executeQuery();
			
			while(res.next()) {
				Widget widget = new Widget(res.getInt("id"), res.getString("name"), 
						res.getInt("width"), res.getInt("height"), res.getString("cssClass"), 
						res.getString("cssStyle"), res.getString("text"), res.getInt("order"));
				list.add(widget);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int updateWidget(int widgetId, Widget widget) {
		// TODO Auto-generated method stub
		int res = 0;
		try {
			java.sql.Connection conn = Connection.getConnection();
			PreparedStatement prepStatement = conn.prepareStatement(UPDATE_WIDGET);
			
			//System.out.println("!!!!!!!!!!!!!!!  " + widget.getPage());
			prepStatement.setInt(1, widgetId);
			prepStatement.setString(2, widget.getName());
			prepStatement.setString(3, widget.getDtype());
			prepStatement.setString(4, widget.getText());
			prepStatement.setInt(5, widget.getOrder());
			prepStatement.setInt(6, widget.getWidth());
			prepStatement.setInt(7, widget.getHeight());
			prepStatement.setInt(8, widget.getPage());
			prepStatement.setInt(9, widgetId);
			
			res = prepStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int deleteWidget(int widgetId) {
		// TODO Auto-generated method stub
		int res = 0;
		try {
			java.sql.Connection conn = Connection.getConnection();
			PreparedStatement prepStatement = conn.prepareStatement(DELETE_WIDGET);
			
			prepStatement.setInt(1, widgetId);
			
			res = prepStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

}
