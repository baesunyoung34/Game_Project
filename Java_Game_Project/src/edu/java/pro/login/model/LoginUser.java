package edu.java.pro.login.model;

import java.io.Serializable;

public class LoginUser implements Serializable {
	public interface Entity{
		String TBL_NAME = "USERDB";
		String COL_ID = "ID";
		String COL_PW = "PASSWORD";
		String COL_NAME = "NAME";
		String COL_PHONE = "PHONE";
		String COL_BIRTH = "BIRTH";
	}	
		
	// 기본정보
	private String id; // pk
	private String pw;
	private String name;
	private String phone;
	private String birth; // DB 저장
	  
	    
	    
	    
	  
	// Constructor;
	public LoginUser(String id, String pw, String name, String phone, String birth) {
			this.id = id;
			this.pw = pw;
			this.name = name;
			this.phone = phone;
			this.birth = birth;
		}
	
	
	// getter setter

	public String getId() {
		return id;
	} // 아이디는 변경이 불가능

	public String getPw() {
		return pw;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getBirth() {
		return birth;
	}


	public void setBirth(String birth) {
		this.birth = birth;
	}
	
	@Override
	public String toString() {
		return "Login(id = " + this.id + " pw = " + this.pw
				+ " name = " + this.name + " phone = " + this.phone + " birth " + this.birth;
	}
	
}
