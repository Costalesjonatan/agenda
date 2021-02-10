package com.agenda.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agenda.web.model.UserEntity;
import com.agenda.web.service.UserServiceInterface;

@Controller
@RequestMapping("/private")
public class PrivateController {
	
	@Autowired
	private UserServiceInterface usuarioService;
	
	@GetMapping("/home")
	public String index(Authentication auth, HttpSession session) {
		String username = auth.getName();
		if(session.getAttribute("usuario") == null) {
			UserEntity usuario = usuarioService.findbyUsername(username);
			usuario.setPassword(null);
			session.setAttribute("usuario", usuario);
		}
		return "home";
	}
	
}
