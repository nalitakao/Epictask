package br.com.fiap.epictask.controller.api;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.epictask.model.Signin;
import br.com.fiap.epictask.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class ApiUserController {
	
	@Autowired
	private UserRepository userrepo;
	
	@GetMapping //userindex
	@Cacheable("users")
	public Page<Signin> userindex(@RequestParam(required = false) String username,
			@PageableDefault Pageable pageable) {
		if (username == null) return userrepo.findAll(pageable);
		
		return userrepo.findByUsernameContaining(username, pageable);
	}
	
	@PostMapping //createuser
	@CacheEvict(value = "users", allEntries = true)
	public ResponseEntity<Signin> createuser(@RequestBody @Valid Signin user,
			UriComponentsBuilder uriBuilder) {
		userrepo.save(user);
		URI uri = uriBuilder.path("api/user/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}
	
	@GetMapping("{id}") //getuser
	public ResponseEntity<Signin> getuser(@PathVariable Long id) {
		Optional<Signin> user = userrepo.findById(id);

		return ResponseEntity.of(user);
	}
	
	@DeleteMapping("{id}") //delete
	@CacheEvict(value = "users", allEntries = true)
	public ResponseEntity<Signin> delete(@PathVariable Long id) {
		Optional<Signin> user = userrepo.findById(id);
		
		if(user.isEmpty()) return ResponseEntity.notFound().build();
		
		userrepo.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("{id}") //updateuser
	@CacheEvict(value = "users", allEntries = true)
	public ResponseEntity<Signin> updateuser(@PathVariable Long id, @RequestBody Signin newUser) {
		Optional<Signin> optional = userrepo.findById(id);
		
		if (optional.isEmpty()) return ResponseEntity.notFound().build();
		
		Signin user = optional.get();
		user.setUsername(newUser.getUsername());
		user.setEmail(newUser.getEmail());
		user.setPassword(newUser.getPassword());
		
		userrepo.save(user);
		return ResponseEntity.ok(user);
	}

}
