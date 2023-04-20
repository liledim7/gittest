package com.jdbc.controller;

import java.util.List;

import com.jdbc.common.MemberController;
import com.jdbc.model.dao.MemberDao;
import com.jdbc.model.dto.MemberDto;
import com.jdbc.model.service.MemberService;
import com.jdbc.view.MainView;

public class MemberControllerImpl implements MemberController{

	//private MemberDao dao = new MemberDao();
	private MemberService service = new MemberService();
	@Override
	public void mainMenu() {
		new MainView().mainMenu();
	}
	
	@Override
	public void selectAllMember() {
		List<MemberDto> members = service.selectAllMember();
		new MainView().printMembers(members);
	}

	@Override
	public void searchMemberById() {
		String id = new MainView().inputData("아이디");
		MemberDto m = service.searchMemberById(id);
		new MainView().printMember(m);
		
	}

	@Override
	public void searchMemberByName() {
		String name = new MainView().inputData("이름");
		List<MemberDto> members = service.searchByName(name);
		new MainView().printMembers(members);
		
	}

	@Override
	public void insertMember() {
		MemberDto m = new MainView().inputMember();
		int result = service.insertMember(m);
		new MainView().printMsg(result>0?"회원가입성공":"회원가입실패");
		
	}
	
	@Override
	public void updateMember() {
		MemberDto m = new MainView().updateMember();
		int result = service.updateMember(m);
		new MainView().printMsg(result>0?"회원수정성공":"회원수정실패");
		
	}

	

	@Override
	public void deleteMember() {
		String id = new MainView().deleteMember();
		int result = service.deleteMember(id);
		new MainView().printMsg(result>0?"회원삭제성공":"회원삭제실패");
		
	}

	
	
}
