package com.dansoft.redesocial.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.dansoft.redesocial.controller.Form.UsuarioForm;
import com.dansoft.redesocial.controller.dto.UsuarioDTO;
import com.dansoft.redesocial.model.Usuario;
import com.dansoft.redesocial.repository.UsuarioRepository;

@Service
@Qualifier("v2")
public class UsuarioServiceV2 extends UsuarioServiceV1 {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario findByNome(String nome) {
		return usuarioRepository.findByNome(nome);
	}

	public List<Usuario> findAll() {
		List<Usuario> usuarios = usuarioRepository.findAll();

		List<Usuario> usuariosAtivos = new ArrayList<>();

		for (Usuario usuario : usuarios) {
			if (!usuario.getDeleted())
				usuariosAtivos.add(usuario);
		}

		return usuariosAtivos;
	}

	public Usuario findUser(Integer id) throws NotFoundException {
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

		if (usuarioOptional.isEmpty())
			throw new NotFoundException();
		return usuarioOptional.get();

	}

	public Usuario saveUser(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	public Usuario deleteUser(Integer id) throws NotFoundException {
		Usuario usuario = findUser(id);

		if (usuario == null || usuario.getDeleted() == true)
			throw new NotFoundException();

		usuario.setDeleted(true);

		return usuarioRepository.save(usuario);
	}

	public Usuario userUpdate(Integer id, UsuarioForm usuarioForm) throws NotFoundException, BadRequestException {
		Usuario usuario = findUser(id);

		if (usuario == null || usuario.getDeleted() == true)
			throw new NotFoundException();

		usuario.setNome(usuarioForm.getNome());
		usuario.setEmail(usuarioForm.getEmail());
		usuario.setSenha(usuarioForm.getSenha());

		return saveUser(usuario);

	}

	public List<UsuarioDTO> listFriends(Integer id) throws NotFoundException {
		Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new NotFoundException());

		List<UsuarioDTO> amigosDTO = new ArrayList<>();

		if (usuario.getAmigos() != null) {
			amigosDTO = usuario.getAmigos().stream().map(UsuarioDTO::new).collect(Collectors.toList());
		}
		return amigosDTO;
	}

	public void addFriend(Integer userId, Integer amigoId) throws NotFoundException, Exception {
		Usuario usuario = findUser(userId);
		Usuario amigo = findUser(amigoId);

		if (usuario == null || usuario.getDeleted() || amigo == null || amigo.getDeleted()) {
			throw new NotFoundException();
		}

		if (usuario.getAmigos() == null) {
			usuario.setAmigos(new ArrayList<>());
		}

		if (usuario.getAmigos().contains(amigo)) {
			throw new Exception("Usuário já presente na lista.");
		}

		usuario.getAmigos().add(amigo);
		saveUser(usuario);

		if (amigo.getAmigos() == null) {
			amigo.setAmigos(new ArrayList<>());
		}

		amigo.getAmigos().add(usuario);
		saveUser(amigo);
	}

	public void removeFriend(Integer userId, Integer amigoId) throws NotFoundException, Exception {
		Usuario usuario = findUser(userId);
		Usuario amigo = findUser(amigoId);

		if (usuario == null || usuario.getDeleted() || amigo == null || amigo.getDeleted()) {
			throw new NotFoundException();
		}

		if (usuario.getAmigos() == null) {
			usuario.setAmigos(new ArrayList<>());
		}

		if (amigo.getAmigos() == null) {
			amigo.setAmigos(new ArrayList<>());
		}

		if (!usuario.getAmigos().contains(amigo)) {
			throw new NotFoundException();
		}

		usuario.getAmigos().remove(amigo);
		saveUser(usuario);

		amigo.getAmigos().remove(usuario);
		saveUser(amigo);
	}
}
