package com.jdbc.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import static com.jdbc.common.JDBCTemplate.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.jdbc.common.JDBCTemplate;
import com.jdbc.model.dto.MemberDto;

public class MemberDao {

	private Properties sql=new Properties();
	{
		try {
			String path=MemberDao.class.getResource("/sql/member/member_sql.properties").getPath();
			sql.load(new FileReader(path));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public List<MemberDto> selectAllMember(Connection conn){
		Statement stmt = null;
		ResultSet rs = null;
//		String sql="SELECT * FROM MEMBER";
		String sql=this.sql.getProperty("selectMemberAll");
		List<MemberDto> members = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				members.add(getMember(rs));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
		}
		return members;
	}
	
	
	private MemberDto getMember(ResultSet rs) throws SQLException {
		MemberDto m = new MemberDto();
		m.setMemberId(rs.getString("member_id"));
		m.setMemberPwd(rs.getString("member_pwd"));
		m.setMemberName(rs.getString("member_name"));
		m.setGender(rs.getString("gender").charAt(0));
		m.setAge(rs.getInt("age"));
		m.setEmail(rs.getString("email"));
		m.setPhone(rs.getString("phone"));
		m.setAddress(rs.getString("address"));
		m.setHobby(rs.getString("hobby").split(","));
		m.setEnrollDate(rs.getDate("enroll_Date"));
		return m;
	}
	public MemberDto searchMemberById(Connection conn, String id) {
		//sql문제 변수값을 대입할때 자료형 맞춰 편리하게 대입 할 수 있는 preparedStatement 이용
		//preparedStatement는 Statement를 상속받은 인터페이스
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDto m = null;
		//외부의 값을 받아서 sql문을 구성할때 표현과 값대입방식이 다름 -> 외부값을 ?로 표시해서 쿼리문을 작서한다
		//"SELECT * FROM MEMBER WHERE MEMBER_ID=?"
//		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID = ?";
		String sql = this.sql.getProperty("searchMemberById");
		try {
			pstmt = conn.prepareStatement(sql);
			//?표시되어있는 곳에 값을 대입해줘야한다. set자료형(String,int,...)(위치값, 대입할값);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) m = getMember(rs);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return m;
	}
	public List<MemberDto> searchMemberByName(Connection conn, String name){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberDto> members = new ArrayList<>();
//		String sql = "SELECT * FROM MEMBER WHERE MEMBER_NAME like '%'||?||'%'";
		String sql = this.sql.getProperty("searchMemberByName");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				members.add(getMember(rs));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}return members;
	}
	
	public int insertMember(Connection conn, MemberDto m) {
		PreparedStatement pstmt = null;
		int result = 0;
//		String sql = "INSERT INTO MEMBER VALUES(?,?,?,?,?,?,?,?,?,SYSDATE)";
		String sql = this.sql.getProperty("insertMember");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,m.getMemberId());
			pstmt.setString(2,m.getMemberPwd());
			pstmt.setString(3,m.getMemberName());
			pstmt.setString(4,m.getGender()+"");
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7,m.getPhone());
			pstmt.setString(8,m.getAddress());
			pstmt.setString(9,String.join(",",m.getHobby()));
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public int updateMember(Connection conn, MemberDto m) {
		PreparedStatement pstmt = null;
		int result = 0;
//		String sql = "UPDATE MEMBER SET MEMBER_NAME=?, AGE=?, EMAIL=?, ADDRESS=? WHERE MEMBER_ID = ?";
		String sql = this.sql.getProperty("updateMember");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getMemberName());
			pstmt.setInt(2, m.getAge());
			pstmt.setString(3, m.getEmail());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getMemberId());
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
	
	public int deleteMember(Connection conn, String id) {
		PreparedStatement pstmt = null;
		int result = 0;
//		String sql = "DELETE FROM MEMBER WHERE MEMBER_ID = ?";
		String sql = this.sql.getProperty("deleteMember");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}return result;
	}
}













