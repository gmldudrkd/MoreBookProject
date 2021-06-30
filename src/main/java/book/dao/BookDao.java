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
	
	public int insertPostbook(Book book) throws Exception{
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement(
						"INSERT into post (posttitle, postdesc , evaluate,  readDatest,  readDateen,postDate, updateDate) "+
						"VALUES (?,?,?,?,?,NOW(),NOW())"
					);
			stmt.setString(1, book.getPosttitle());
			stmt.setString(2, book.getPostdesc());
			stmt.setString(3, book.getEvaluate());
			stmt.setString(4, book.getReadDatest());
			stmt.setString(5, book.getReadDateen());
			
			return stmt.executeUpdate();
		 }catch(Exception e) {
			 throw e;
		 }finally {
			 try { if(connection != null) connection.close(); } catch(Exception e){ }
		 }
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
	
	//독후감리스트
	public List<Book> booklist() throws Exception{
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT * FROM post ORDER BY no DESC");
			ArrayList<Book> books = new ArrayList<Book>();
			
			while(rs.next()) {
				books.add(new Book().setNo(rs.getInt("no"))
						.setEvaluate(rs.getString("evaluate"))
						.setPosttitle(rs.getString("posttitle"))
						.setPostdesc(rs.getString("postdesc"))
						.setReadDatest(rs.getString("readDatest"))
						.setReadDateen(rs.getString("readDateen"))
						.setUpdateDate(rs.getDate("updateDate"))
						.setPostDate(rs.getDate("postDate")));
			}
			return books;
		}catch(Exception e) {
			throw e;
		}finally {
			try { if(rs != null) rs.close(); } catch(Exception e){ }
			try { if(stmt != null) stmt.close(); } catch(Exception e){ }
			try { if(connection != null) connection.close(); } catch(Exception e){ }
		}	
	}
	
	public Book bookselect(int no) throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT * FROM post where no='"+no+"' ");

			if(rs.next()) {
				return new Book().setNo(rs.getInt("no"))
						.setEvaluate(rs.getString("evaluate"))
						.setPosttitle(rs.getString("posttitle"))
						.setPostdesc(rs.getString("postdesc"))
						.setReadDatest(rs.getString("readDatest"))
						.setReadDateen(rs.getString("readDateen"))
						.setUpdateDate(rs.getDate("updateDate"))
						.setPostDate(rs.getDate("postDate"));
			}else {
		        throw new Exception("해당 번호의 회원을 찾을 수 없습니다.");
	      }
		}catch(Exception e) {
			throw e;
		}finally {
			try { if(rs != null) rs.close(); } catch(Exception e){ }
			try { if(stmt != null) stmt.close(); } catch(Exception e){ }
			try { if(connection != null) connection.close(); } catch(Exception e){ }
		}	
	}
	
	public int updatePostbook(Book book) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement("update post set posttitle=?, postdesc=?, readDatest=?, readDateen=?, updateDate=now() where no=?");
			stmt.setString(1, book.getPosttitle());
			stmt.setString(2, book.getPostdesc());
			stmt.setString(3, book.getReadDatest());
			stmt.setString(4, book.getReadDateen());
			stmt.setInt(5, book.getNo());
			return stmt.executeUpdate();
		}catch(Exception e) {
			throw e;
		}finally {
			try { if(connection != null) connection.close(); } catch(Exception e){ }
		 }
	}

}
