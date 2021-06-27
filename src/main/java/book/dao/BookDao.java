package book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import book.vo.Book;

public class BookDao {
	DataSource ds; // DataSource는 BasicDataSource 의 구현제, 유연성을위해,,

	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}
	
	//회원가입
	public int insertauth(Book book) throws Exception{
		Connection connection = null;
		PreparedStatement stmt = null;
		 try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement(
						"INSERT into member (userid, password, name, createdDate, modifiedDate) "+
						"VALUES (?,?,?,NOW(),NOW())"
					);
			stmt.setString(1, book.getUserid());
			stmt.setString(2, book.getPassword());
			stmt.setString(3, book.getName());
			
			return stmt.executeUpdate();
		 }catch(Exception e) {
			 throw e;
		 }finally {
			 try { if(connection != null) connection.close(); } catch(Exception e){ }
		 }
	}
	
	//회원확인
	public Book exist(String id, String pwd) throws Exception{
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement("select no, userid, name, password, createdDate, modifiedDate  from member where userid='"+id+"' and password='"+pwd+"'");
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				return new Book().setNo(rs.getInt("no")).setUserid(rs.getString("userid")).setName(rs.getString("name")).setPassword(rs.getString("password"))
						.setCreatedDate(rs.getDate("createdDate")).setModifiedDate(rs.getDate("modifiedDate"));
			}else {
		        return null;
		      }
		}catch(Exception e) {
			throw e;
		}finally {
			try { if(connection != null) connection.close(); } catch(Exception e){ }
		 }
	}

}
