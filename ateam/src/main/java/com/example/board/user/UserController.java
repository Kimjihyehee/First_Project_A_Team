package com.example.board.user;

import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {

	private final UserService userService;
	
	@GetMapping("/signUp")
	public String signup(UserCreateForm userCreateForm) {
		return "/pages/signUp";
	}
	
	@PostMapping("/signUp")
	public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "/pages/signUp";
		}
		//�н����尡 ��ġ���� ������ ó���ϴ� ���.
		if(!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
			//rejectValue �ڵ带 �̿��� ������ �߻���Ŵ.
			bindingResult.rejectValue("password2", "passwordIncorrect", "2�� �н����� ��ġ x");
			return "/pages/signUp";
		}
		
		try {
			userService.create(userCreateForm.getUsername(), userCreateForm.getEmail(),
					userCreateForm.getNickname(),userCreateForm.getPassword1());
		}
		catch(DataIntegrityViolationException dive){
			dive.printStackTrace();
			bindingResult.reject("ȸ������ ����", "�̹� ��ϵǾ��ִ� ������Դϴ�");
			return "/pages/signUp";
		}
		catch(Exception e) {
			e.printStackTrace();
			bindingResult.reject("ȸ������ ����", e.getMessage());
			return "/pages/signUp";
			
		}
		
		
		return "redirect:/";
		
	}
	
	@GetMapping("/signIn")
	public String login() {
		return "/pages/signIn";
	}
	
	
	
	
	
	
	
	
}