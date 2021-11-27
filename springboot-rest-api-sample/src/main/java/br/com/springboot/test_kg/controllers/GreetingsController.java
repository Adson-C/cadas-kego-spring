package br.com.springboot.test_kg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public String listAllUsers(@PathVariable String nome , int idade, String email, 
			String cpf , String cep, String logradouro, 
			String bairro, String cidade, String uf, String numero) {

		Usuario users = new Usuario();
		users.setNome(nome);
		users.setIdade(idade);
		users.setEmail(email);
		users.setCpf(cpf);
		users.setCep(cep);
		users.setLogradouro(logradouro);
		users.setBairro(bairro);
		users.setCidade(cidade);
		users.setUf(uf);
		users.setNumero(numero);
		
		

		userRepo.save(users);/* grava no banco de dados */

		return "Ola mundo " + nome;
	}
	
	@PostMapping(value = "save")
	@ResponseBody
	public ResponseEntity<Usuario> save(@RequestBody Usuario user) {
		Usuario users = userRepo.save(user);
		
		return new ResponseEntity<Usuario>(users, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "update")
	@ResponseBody
	public ResponseEntity<?> update(@RequestBody Usuario user) {
		
		if (user.getId() == null) {
			return new ResponseEntity<String>("Id não foi informado para update.", HttpStatus.OK);
			
		} else {

		}
		Usuario users = userRepo.saveAndFlush(user);
		return new ResponseEntity<Usuario>(users, HttpStatus.OK);
		
	}
	
	 @DeleteMapping(value = "delete")
	  @ResponseBody
	  public ResponseEntity<String> delete(@RequestParam Long iduser) {
		  
		  	userRepo.deleteById(iduser);
			
			return new ResponseEntity<String>("User deletado com sucesso!", HttpStatus.OK);
	  }
	
	  @GetMapping(value = "listAll")
	  @ResponseBody // retorna os dados para o corpo da resposta public
	  public ResponseEntity<List<Usuario>> listUser() { 
		  List<Usuario> users = userRepo.findAll();
	  
	  return new ResponseEntity<List<Usuario>>(users, HttpStatus.OK);
	  
	  }
	  
	  @GetMapping(value = "bucaruserid")
	  @ResponseBody
	  public ResponseEntity<Usuario> bucaruserid(@RequestParam(name = "iduser") Long iduser) {
		  
		  Usuario users =	 userRepo.findById(iduser).get();
			
			return new ResponseEntity<Usuario>(users, HttpStatus.OK);
	  }
	  
	 
	  @GetMapping(value = "bucaporname")
	  @ResponseBody
	  public ResponseEntity<List<Usuario>> bucaporname(@RequestParam(name = "name") String name) {
		  
		 List<Usuario>  users =	 userRepo.buscaPorName(name.trim().toLowerCase());
			
			return new ResponseEntity<List<Usuario>>(users, HttpStatus.OK);
	  }
	  
	 
}
