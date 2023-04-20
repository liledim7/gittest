package com.jdbc.model.service;

import java.sql.Connection;
import java.util.List;

import com.jdbc.common.JDBCTemplate;
import com.jdbc.model.dao.MemberDao;
import com.jdbc.model.dto.MemberDto;
import static com.jdbc.common.JDBCTemplate.*;
public class MemberService {
	private MemberDao dao = new MemberDao(); 
	
	//DB에 연결하는 Connection객체를 관리
	//트랜잭션처리를 여기서 함
	//서비스에 해당하는 DAO클래스를 호출해서 연결DB에서 sql문을 실행시키는 기능
	public List<MemberDto> selectAllMember(){
		Connection conn = getConnection();
		List<MemberDto> members = dao.selectAllMember(conn);
		close(conn);
		return members;
	}
	
	public MemberDto searchMemberById(String id) {
		Connection conn = getConnection();
		MemberDto m = dao.searchMemberById(conn, id);
		close(conn);
		return m;
	}
	
	public List<MemberDto> searchByName(String name){
		Connection conn = getConnection();
		List<MemberDto> members = dao.searchMemberByName(conn, name);
		close(conn);
		return members;
	}
	
	public int insertMember(MemberDto m) {
		Connection conn = getConnection();
		int result = dao.insertMember(conn, m);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int updateMember(MemberDto m) {
		Connection conn = getConnection();
		int result = dao.updateMember(conn, m);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public int deleteMember(String id) {
		Connection conn = getConnection();
		int result = dao.deleteMember(conn, id);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
}


















