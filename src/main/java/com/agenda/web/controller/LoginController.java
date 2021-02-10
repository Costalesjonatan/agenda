package com.agenda.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.agenda.web.model.UserEntity;
import com.agenda.web.service.UserServiceInterface;

@Controller
public class LoginController {
	
	@Autowired
	private UserServiceInterface usuarioService;
	
	@GetMapping("/auth/login")
	public String login(Model model) {
		model.addAttribute("usuario", new UserEntity());
		return "login";
	}
	
	@GetMapping("/auth/registro")
	public String registro(Model model) {
		model.addAttribute("usuario", new UserEntity());
		
		return "registro";
	}
	
	@PostMapping("/auth/registro")
	public String registro(@Valid @ModelAttribute UserEntity usuario, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "redirect:/auth/registro";
		}
		else {
			model.addAttribute("usuario", usuarioService.registrar(usuario));
		}
		
		return "redirect:/auth/login";
	}
}
