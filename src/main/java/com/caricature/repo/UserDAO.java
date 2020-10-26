package com.caricature.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.caricature.model.User;
import com.caricature.util.DAOConnection;
import com.caricature.util.DAOInterface;

public class UserDAO implements DAOInterface<User, Integer>{

	private static Logger log = Logger.getLogger(UserDAO.class);
	
	@Override
	public List<User> getAll() {
		
		List<User> uList = new ArrayList<>();
		Connection con;
		
		try {
			con = DAOConnection.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("select * from user_view");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				User user = new User();
				
				user.setId(rs.getInt("user_id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("pass"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setRole(rs.getString("user_role"));
				
				uList.add(user);
			}
			
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			uList = null;
		}
		
		return uList;
	}

	@Override
	public User get(Integer idVal) {
		User user = null;
		Connection con;
		
		try {
			con = DAOConnection.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("select * from user_view where user_id = ?");
			
			stmt.setInt(1,  idVal);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				user= new User();
				
				user.setId(rs.getInt("user_id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("pass"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setRole(rs.getString("user_role"));
			}
			
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			user = null;
		}
		
		return user;
	}
	
	//Overload
	public User get(String username, String password) {
		User user = null;
		Connection con;
		
		try {
			con = DAOConnection.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("select * from user_view where username = ? and pass = ?");
			
			stmt.setString(1, username);
			stmt.setString(2, password);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				user = new User();
				
				user.setId(rs.getInt("user_id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("pass"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setRole(rs.getString("user_role"));
			}
			
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			user = null;
		}
		
		return user;
	}

	public boolean isUser(String username)
	{
		Connection con;
		
		try {
			con = DAOConnection.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("select * from user_view where username = ?");
			
			stmt.setString(1, username);

			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.isBeforeFirst())
			{
				rs.close();
				stmt.close();
				log.info("username found!");
				return true;
			}
			else
			{
				rs.close();
				stmt.close();
				log.error("username not found in db");
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean create(User t) {
		Connection con;
		
		try {
			con = DAOConnection.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("insert into users (username, pass, "
					+ "first_name, last_name, email, role_id) values (?,?,?,?,?,?)");
			
			stmt.setString(1, t.getUsername());
			stmt.setString(2, t.getPassword());
			stmt.setString(3, t.getFirstName());
			stmt.setString(4, t.getLastName());
			stmt.setString(5, t.getEmail());
			stmt.setInt(6, t.getRoleId());
			
			if(stmt.executeUpdate() != 0)
			{
				stmt.close();
				return true;//success
			}
			else
			{
				stmt.close();
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean delete(Integer idVal) {
		Connection con;
		
		try {
			con = DAOConnection.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("delete from users where user_id = ?");
			
			stmt.setInt(1, idVal);
			
			if(stmt.executeUpdate() != 0)
			{
				stmt.close();
				return true;
			}
			else
			{
				stmt.close();
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	
}
