package com.jdbc.view;

import java.util.List;
import java.util.Scanner;

import com.jdbc.common.MemberController;
import com.jdbc.controller.MemberControllerImpl;
import com.jdbc.model.dto.MemberDto;

public class MainView {

	private MemberController controller = new MemberControllerImpl();
	
	public void mainMenu() {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("==== jdbc 회원관리 프로그램 ====");
			System.out.println("1. 전체회원조회");
			System.out.println("2. 아이디로 회원조회");
			System.out.println("3. 이름으로 회원조회");
			System.out.println("4. 회원등록");
			System.out.println("5. 회원수정(이름, 나이, 이메일, 주소)");
			System.out.println("6. 회원삭제");
			System.out.println("7. 메뉴추가");
			System.out.println("0. 프로그램 종료");
			System.out.print("메뉴입력 : ");
			int menu = sc.nextInt();
			switch(menu) {
				case 1 : controller.selectAllMember();break;
				case 2 : controller.searchMemberById();;break;
				case 3 : controller.searchMemberByName();break;
				case 4 : controller.insertMember();break;
				case 5 : controller.updateMember();break;
				case 6 : controller.deleteMember();break;
				case 0 : System.out.println("프로그램을 종료합니다.");return;
				default : System.out.println("0~5사이의 메뉴를 입력하세요");
			}
			
		}
	}
	public void printMembers(List<MemberDto> members) {
		System.out.println("==== 조회된 결과 ====");
		members.forEach(m->System.out.println(m));
		System.out.println("==================");
	}
	public void printMember(MemberDto members) {
		System.out.println("==== 조회된 결과 ====");
		System.out.println(members!=null?members:"검색결과가 없습니다.");
		System.out.println("==================");
	}
	
	public String inputData(String title) {
		Scanner sc = new Scanner(System.in);
		System.out.println("검색할 "+title+ "입력");
		System.out.print("입력 : ");
		String data = sc.next();
		return data;
	}
	
	public MemberDto inputMember() {
		Scanner sc = new Scanner(System.in);
		System.out.println("==== 등록할 회원 정보 입력 ====");
		System.out.print("아이디: ");
		String id = sc.next();
		System.out.print("비밀번호: ");
		String pwd = sc.next();
		System.out.print("이름: ");
		String name = sc.next();
		System.out.print("성별(M/F): ");
		char gender = sc.next().charAt(0);
		System.out.print("나이: ");
		int age = sc.nextInt();
		System.out.print("이메일: ");
		String email = sc.next();
		System.out.print("핸드폰번호: ");
		String phone = sc.next();
		System.out.print("주소: ");
		sc.nextLine();
		String address = sc.nextLine();
		System.out.print("취미: ");
		String[] hobby = sc.nextLine().split(","); 
		return new MemberDto(id, pwd, name, gender, age, email, phone, address, hobby, null );
		
	}
	
	public MemberDto updateMember() {
		Scanner sc = new Scanner(System.in);
		MemberDto m = new MemberDto();
		System.out.println("==== 회원정보 수정 ====");
		System.out.print("수정할 회원아이디: ");
		m.setMemberId(sc.nextLine());
		System.out.print("새 이름: ");
		m.setMemberName(sc.nextLine());
		System.out.print("새 나이: ");
		m.setAge(sc.nextInt());
		sc.nextLine();
		System.out.print("새 이메일: ");
		m.setEmail(sc.nextLine());
		System.out.print("새 주소: ");
		m.setAddress(sc.nextLine());
		return m;
		
		
	}
	
	public String deleteMember() {
		Scanner sc = new Scanner(System.in);
		System.out.print("삭제할 아이디: ");
		String id = sc.nextLine();
		return id;
	}
	
	public void printMsg(String msg) {
		System.out.println(msg);
	}
	
}















