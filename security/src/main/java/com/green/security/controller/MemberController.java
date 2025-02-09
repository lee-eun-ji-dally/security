package com.green.security.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.green.security.config.CustomUserDetails;

@Controller
@RequestMapping("/members")
public class MemberController {
	
	@RequestMapping("/")
	public String welcome(Model model, Principal principal, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
		// 첫번째 방법))
		System.out.println("member welcome~~~~~~~~!!! ^0^/ !!!");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails)authentication.getPrincipal();
		
		model.addAttribute("username", userDetails.getUsername());
		model.addAttribute("name", userDetails.getName());

		// 두번째 방법)) Principal 객체 주입 : 사용자 인증 정보를 가진 객체
		model.addAttribute("username2", principal.getName());	//getName : id를 뜻함
		
		// 세번째 방법)) @AuthenticationPrincipal을 사용하여 UserDetails 인터페이스의 구현체 CustomUserDetails 객체를 주입받아 사용하기
		model.addAttribute("username3",customUserDetails.getUsername());
		model.addAttribute("name3",customUserDetails.getName());
		model.addAttribute("role3",customUserDetails.getRole());
		
		return "members/welcome";
	}
}
