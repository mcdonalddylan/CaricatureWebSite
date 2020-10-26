package com.caricature.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.caricature.model.Reimbursement;
import com.caricature.model.User;
import com.caricature.util.DAOConnection;
import com.caricature.util.DAOInterface;

public class ReimburseDAO implements DAOInterface<Reimbursement, Integer>{

	private static Logger log = Logger.getLogger(ReimburseDAO.class);

	@Override
	public List<Reimbursement> getAll() {
		List<Reimbursement> rList = new ArrayList<>();
		Connection con;
		
		try {
			con = DAOConnection.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("select * from reim_view");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				Reimbursement reim = new Reimbursement();
				
				reim.setId(rs.getInt("reim_id"));
				reim.setAmount(rs.getInt("amount"));
				reim.setSubmitDate(rs.getTimestamp("submitted"));
				reim.setResolveDate(rs.getTimestamp("resolved"));
				reim.setDescription(rs.getString("description"));
				reim.setRecipt(rs.getBytes("recipt"));
				
				User tempUser = new User();
				tempUser.setId(rs.getInt("author_id"));
				tempUser.setFirstName(rs.getString("author_first"));
				tempUser.setLastName(rs.getString("author_last"));
				reim.setAuthor(tempUser);
				User tempUser2 = new User();
				tempUser2.setId(rs.getInt("resolver_id"));
				tempUser2.setFirstName(rs.getString("resolver_first"));
				tempUser2.setLastName(rs.getString("resolver_last"));
				reim.setResolver(tempUser2);
				
				reim.setType(rs.getString("reim_type"));
				reim.setStatus(rs.getString("reim_status"));
				
				
				rList.add(reim);
			}
			
			rs.close();
			stmt.close();
			
			log.info("successfully retrieved all reimburesment data");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("unable to retrieve reimbursement data");
			rList = null;
		}
		
		return rList;
	}
	
	public List<Reimbursement> getAll(Integer userId) {
		List<Reimbursement> rList = new ArrayList<>();
		Connection con;
		
		try {
			con = DAOConnection.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("select * from reim_view where author_id = ? order by submitted desc");
			
			stmt.setInt(1, userId);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				Reimbursement reim = new Reimbursement();
				
				reim.setId(rs.getInt("reim_id"));
				reim.setAmount(rs.getInt("amount"));
				reim.setSubmitDate(rs.getTimestamp("submitted"));
				reim.setResolveDate(rs.getTimestamp("resolved"));
				reim.setDescription(rs.getString("description"));
				reim.setRecipt(rs.getBytes("recipt"));
				
				User tempUser = new User();
				tempUser.setId(rs.getInt("author_id"));
				tempUser.setFirstName(rs.getString("author_first"));
				tempUser.setLastName(rs.getString("author_last"));
				reim.setAuthor(tempUser);
				User tempUser2 = new User();
				tempUser2.setId(rs.getInt("resolver_id"));
				tempUser2.setFirstName(rs.getString("resolver_first"));
				tempUser2.setLastName(rs.getString("resolver_last"));
				reim.setResolver(tempUser2);
				
				reim.setType(rs.getString("reim_type"));
				reim.setStatus(rs.getString("reim_status"));
				
				
				rList.add(reim);
			}
			
			rs.close();
			stmt.close();
			
			log.info("successfully retrieved all reimburesment data of user: " +
			rList.get(0).getAuthor().getFirstName());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("unable to retrieve user specific reimbursement data");
			rList = null;
		}
		
		return rList;
	}

	@Override
	public Reimbursement get(Integer idVal) {
		Reimbursement reim = null;
		Connection con;
		
		try {
			con = DAOConnection.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("select * from reim_view where author_id = ? order by submitted asc");
			
			stmt.setInt(1, idVal);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				reim = new Reimbursement();
				
				reim.setId(rs.getInt("reim_id"));
				reim.setAmount(rs.getInt("amount"));
				reim.setSubmitDate(rs.getTimestamp("submitted"));
				reim.setResolveDate(rs.getTimestamp("resolved"));
				reim.setDescription(rs.getString("description"));
				reim.setRecipt(rs.getBytes("recipt"));
				
				User tempUser = new User();
				tempUser.setId(rs.getInt("author_id"));
				tempUser.setFirstName(rs.getString("author_first"));
				tempUser.setLastName(rs.getString("author_last"));
				reim.setAuthor(tempUser);
				User tempUser2 = new User();
				tempUser2.setId(rs.getInt("resolver_id"));
				tempUser2.setFirstName(rs.getString("resolver_first"));
				tempUser2.setLastName(rs.getString("resolver_last"));
				reim.setResolver(tempUser);
				
				reim.setType(rs.getString("reim_type"));
				reim.setStatus(rs.getString("reim_status"));
			}
			
			rs.close();
			stmt.close();
			
			log.info("successfully retrieved reimburesment data of user: " +
			reim.getAuthor().getFirstName());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("unable to retrieve user specific reimbursement data");
			reim = null;
		}
		
		return reim;
	}

	@Override
	public boolean create(Reimbursement t) {
		Connection con;
		
		try {
			con = DAOConnection.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("insert into reimbursement (amount,"
					+ "submitted, resolved, description, recipt, author, resolver, status_id, type_id) "
					+ "values (?,?,?,?,?,?,?,?,?)");
			
			stmt.setInt(1, (int)t.getAmount());
			stmt.setTimestamp(2, t.getSubmitDate());
			stmt.setTimestamp(3, t.getResolveDate());
			stmt.setString(4, t.getDescription());
			stmt.setBytes(5, t.getRecipt());
			stmt.setInt(6, t.getAuthorId());
			stmt.setInt(7, t.getResolverId());
			stmt.setInt(8, t.getStatusId());
			stmt.setInt(9, t.getTypeId());
			
			if(stmt.executeUpdate() != 0)
			{
				stmt.close();
				log.info("reimbursement successfully created!");
				return true;//success
			}
			else
			{
				stmt.close();
				log.error("reimbursement was not created");
				return false;
			}
		} catch (SQLException e) {
			log.error("reimbursement was not created. SQL error");
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean update(Integer reimId, Integer resolverId, Integer statusId) {
		Connection con;
		
		try {
			con = DAOConnection.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("update reimbursement set resolved = ?, "
					+ "resolver = ?, status_id = ? where reim_id = ?");
			
			stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
			stmt.setInt(2, resolverId);
			stmt.setInt(3, statusId);
			stmt.setInt(4, reimId);

			
			if(stmt.executeUpdate() != 0)
			{
				stmt.close();
				log.info("reimbursement successfully created!");
				return true;//success
			}
			else
			{
				stmt.close();
				log.error("reimbursement was not created");
				return false;
			}
		} catch (SQLException e) {
			log.error("reimbursement was not created. SQL error");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Integer idVal) {
		Connection con;
		
		try {
			con = DAOConnection.getInstance().getConnection();
			PreparedStatement stmt = con.prepareStatement("delete from reimbursement where reim_id = ?");
			
			stmt.setInt(1, idVal);
			
			if(stmt.executeUpdate() != 0)
			{
				stmt.close();
				log.info("reimbursement successfully DELETED!");
				return true;
			}
			else
			{
				stmt.close();
				log.error("reimbursement was unable to be deleted");
				return false;
			}
		} catch (SQLException e) {
			log.error("reimbursement was unable to be deleted. SQL error");
			e.printStackTrace();
			return false;
		}
	}
	
	
}
