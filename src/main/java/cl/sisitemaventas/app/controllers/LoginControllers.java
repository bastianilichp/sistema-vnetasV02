package cl.sisitemaventas.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import cl.sisitemaventas.app.entity.Usuario;
import cl.sisitemaventas.app.repository.UsuarioRepositorio;
import jakarta.servlet.http.HttpSession;


@Controller
public class LoginControllers {

	@Autowired
	private UsuarioRepositorio userRepository;
	
	

	@GetMapping("/login")
	public String ingresarApp() {
		return "login";

	}

	@GetMapping("/inicioSession")
	public String inicioSession(@RequestParam String email, @RequestParam String password, HttpSession session) {

		Usuario user = new Usuario();

		user = userRepository.findByEmail(email);
		if(user != null) {
			if (BCrypt.checkpw(password, user.getPassword())) {					      
				 session.setAttribute("user", user);
	           
				 return "redirect:/index";
			}else {
				
				return "login";
			}
			
		}else {
			return "login";
			
		}
		

		
	}
	
	

}
