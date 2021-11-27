package br.com.springboot.test_kg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.test_kg.model.Usuario;
import br.com.springboot.test_kg.repository.UsuarioRepository;


@RestController
public class GreetingsController {
	@Autowired
	private UsuarioRepository userRepo;

	@RequestMapping(value = "/listUser/{nome}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String listAllUsers(@PathVariable String nome , int idade, String cpf) {

		Usuario users = new Usuario();
		users.setNome(nome);
		users.setIdade(idade);
		users.setCpf(cpf);
		

		userRepo.save(users);/* grava no banco de dados */

		return "Ola mundo " + nome;
	}
	
	@PostMapping(value = "save")
	@ResponseBody
	public ResponseEntity<Usuario> save(@RequestBody Usuario user) {
		Usuario users = userRepo.save(user);
		
		return new ResponseEntity<Usuario>(users, HttpStatus.CREATED);
	}
	
	  @GetMapping(value = "listAll")
	  @ResponseBody // retorna os dados para o corpo da resposta public
	  public ResponseEntity<List<Usuario>> listUser() { 
		  List<Usuario> users = userRepo.findAll();
	  
	  return new ResponseEntity<List<Usuario>>(users, HttpStatus.OK);
	  
	  }
	 
	  @DeleteMapping(value = "delete")
	  @ResponseBody
	  public ResponseEntity<String> delete(@RequestParam Long iduser) {
		  
		  	userRepo.deleteById(iduser);
			
			return new ResponseEntity<String>("User deletado com sucesso!", HttpStatus.OK);
	  }
	 
}
