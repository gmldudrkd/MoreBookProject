package book.vo;

import java.util.Date;

import book.vo.Book;

public class Book {
	protected int no;
	protected String name;
	protected String userid;
	protected String password;
	protected Date createdDate;
	protected Date modifiedDate;
	
	protected String posttitle;
	protected String postdesc;
	protected Date postDate;
	protected String readDatest;
	protected String readDateen;
	protected Date updateDate;
	protected String evaluate;
	
	
	public String getPosttitle() {
		return posttitle;
	}
	public Book setPosttitle(String posttitle) {
		this.posttitle = posttitle;
		return this;
	}
	public String getPostdesc() {
		return postdesc;
	}
	public Book setPostdesc(String postdesc) {
		this.postdesc = postdesc;
		return this;
	}
	public Date getPostDate() {
		return postDate;
	}
	public Book setPostDate(Date postDate) {
		this.postDate = postDate;
		return this;
	}
	public String getReadDatest() {
		return readDatest;
	}
	public Book setReadDatest(String readDatest) {
		this.readDatest = readDatest;
		return this;
	}
	public String getReadDateen() {
		return readDateen;
	}
	public Book setReadDateen(String readDateen) {
		this.readDateen = readDateen;
		return this;
	}
	public String getEvaluate() {
		return evaluate;
	}
	public Book setEvaluate(String evaluate) {
		this.evaluate = evaluate;
		return this;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public Book setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
		return this;
	}
	
	public int getNo() {
		return no;
	}
	public Book setNo(int no) {
		this.no = no;
		return this;
	}
	public String getName() {
		return name;
	}
	public Book setName(String name) {
		this.name = name;
		return this;
	}
	public String getUserid() {
		return userid;
	}
	public Book setUserid(String userid) {
		this.userid = userid;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public Book setPassword(String password) {
		this.password = password;
		return this;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public Book setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
		return this;
	}
	public Date ModifiedDate() {
		return modifiedDate;
	}
	public Book setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
		return this;
	}
}
