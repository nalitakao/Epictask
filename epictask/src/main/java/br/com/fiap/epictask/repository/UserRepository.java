package br.com.fiap.epictask.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.epictask.model.Signin;

public interface UserRepository extends JpaRepository<Signin, Long> {

	Page<Signin> findByUsernameContaining(String username, Pageable pageable);

}
