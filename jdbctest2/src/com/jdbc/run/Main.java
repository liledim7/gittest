package com.jdbc.run;

import com.jdbc.controller.MemberControllerImpl;

public class Main {
	public static void main(String[] args) {
		new MemberControllerImpl().mainMenu();
		System.out.println("내가 추가한것");
	}
}
