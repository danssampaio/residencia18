package com.dansoft.redesocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.dansoft.redesocial.model.UsuarioLogin;

@Repository
public interface UsuarioLoginRepository extends JpaRepository<UsuarioLogin, Integer> {
	UserDetails findByLogin(String login);
}
