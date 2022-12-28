package com.example.board.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {

	@Size(min = 3, max =20)
	@NotEmpty(message="ID�� 3~20���ڷ� �Է��ϼ���")
	private String username;
	
	@NotEmpty(message="�̸����� �Է��ϼ���")
	@Email
	private String email;
	
	@NotEmpty(message="�г����� �Է��ϼ���")
	private String nickname;
	
	@NotEmpty(message="��й�ȣ�� �Է��ϼ���")
	private String password1;
	
	@NotEmpty(message="��й�ȣ�� Ȯ�����ּ���")
	private String password2;

	
}