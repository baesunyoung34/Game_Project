package edu.java.pro.login.controller;

import java.awt.Frame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.JOptionPane;

import edu.java.pro.login.model.LoginUser;
import edu.java.pro.login.ojdbc.OracleConnect;
import edu.java.pro.login.view.LoginUserMain;
import oracle.jdbc.OracleDriver;

import static edu.java.pro.login.model.LoginUser.Entity.*;
import static edu.java.pro.login.ojdbc.OracleConnect.*;

public class LoginDaoImpl implements LoginDao {

	private static LoginDaoImpl instance = null;

	private LoginDaoImpl() {
	}

	public static LoginDaoImpl getInstance() {
		if (instance == null) {
			instance = new LoginDaoImpl();
		}
		return instance;
	}

	// 오라클 연동
	private Connection getConnection() throws SQLException {
		DriverManager.registerDriver(new OracleDriver());
		// DB 접속
		Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
		return conn;
	}

	private void CloseResources(Connection conn, Statement stmt) throws SQLException {
		stmt.close();
		conn.close();
	}

	private void CloseResources(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
		rs.close();
		CloseResources(conn, stmt);
	}

	// insert 회원가입 등록
	private static final String SQL_INSERT = " insert into " + TBL_NAME + " (" + COL_ID + ", " + COL_PW + ", "
			+ COL_NAME + ", " + COL_PHONE + ", " + COL_BIRTH + ")" + "values(?, ?, ?, ?, ?)";

	// 중복된 아이디가 있을 경우 회원가입 불가
	// "SELECT id FROM USERDB WHERE id = ?";
	private static final String SQL_SELECT_BY_ID = "select * from " + TBL_NAME + " where " + COL_ID + " = ?";

	@Override
	public int create(LoginUser loginuser) {
		int result = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			// 중복체크
			stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
			stmt.setString(1, loginuser.getId());
			rs = stmt.executeQuery();
			if (rs.next()) { // 중복된 아이디가 있는 경우
				return -1; // 중복이 되었다는 의미로 -1를 반환
			}
			System.out.println("중복된 로그인");
			// 중복이 없을 경우 회원가입 쿼리문 실행
			System.out.println(SQL_INSERT);
			stmt = conn.prepareStatement(SQL_INSERT);
			stmt.setString(1, loginuser.getId());
			stmt.setString(2, loginuser.getPw());
			stmt.setString(3, loginuser.getName());
			stmt.setString(4, loginuser.getPhone());
			stmt.setString(5, loginuser.getBirth());

			result = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				CloseResources(conn, stmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 로그인 시도시 아이디 비밀번호가 맞는지 확인
	private static final String SQL_SELECT_BY_KEYWORD = "SELECT * FROM USERDB WHERE id = ?" + " AND password = ?";

	public boolean login(String id, String password) {
		System.out.println("id=" + id + "pw=" + password);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(SQL_SELECT_BY_KEYWORD);
			System.out.println(SQL_SELECT_BY_KEYWORD);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("성공");	
				return true;
			} else {
				System.out.println("실패");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false; // 로그인 실패
	}

}
