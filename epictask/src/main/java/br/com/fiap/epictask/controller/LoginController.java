package br.com.fiap.epictask.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.fiap.epictask.model.Signin;
import br.com.fiap.epictask.repository.UserRepository;

@Controller
public class LoginController {
	
	@Autowired
	private UserRepository repository;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/users")
	public ModelAndView users() {
		ModelAndView modelAndView = new ModelAndView("users");
		List<Signin> users = repository.findAll();
		modelAndView.addObject("users", users);
		return modelAndView;
	}
	
	@RequestMapping("/login/signin")
	public String create(Signin signin) {
		return "signin";
	}
	
	@PostMapping("/users")
	public String save(@Valid Signin signin, BindingResult result) {
		if (result.hasErrors()) return "signin";
		repository.save(signin);
		return "users";
	}

}
