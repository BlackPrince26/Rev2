package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojo.AccountInfo;
import com.revature.pojo.User;
import com.revature.pojo.UserInformation;
import com.revature.util.ConnectionFactory;



public class UserDao {



	public List<User> findAll(){
		List<User> Users = new ArrayList<User>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from bank_user");
			while(rs.next()) {
				User a = new User();
				a.setUserId(rs.getInt(1));
				a.setFirstname(rs.getString(2));
				a.setLastname(rs.getString(3));
				a.setUsername(rs.getString(4));
				a.setUsrPassword(rs.getString(5));
				Users.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Users;
	}

	public User getByUsername(String username) {
		User u = null;

		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select * from bank_user where lower(username) = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, username.toLowerCase()); //compare without case
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				u = new User();
				u.setUserId(rs.getInt(1));
				u.setFirstname(rs.getString(2));
				u.setLastname(rs.getString(3));
				u.setUsername(rs.getString(4));
				u.setUsrPassword(rs.getString(5));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("IN USER DAO");
		System.out.println(u);
		return u;

	}

	public UserInformation getUserInfo(User u) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "select acc.account_id, acc.balance, type.type\n " + 
					"from bank_account acc \n " + 
					"inner join account_type type\n " + 
					"on acc.ACC_TYPE = type.account_type_id\n " + 
					"where acc.owner = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, u.getUserId());

			UserInformation info = new UserInformation();
			info.setUser(u);
			List<AccountInfo> accounts = new ArrayList<AccountInfo>();

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				AccountInfo temp = new AccountInfo();
				temp.setId(rs.getInt(1));
				temp.setBalance(rs.getDouble(2));
				temp.setType(rs.getString(3));
				accounts.add(temp);
			}
			info.setAccounts(accounts);
			return info;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}




}
