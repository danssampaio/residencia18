package com.dansoft.redesocial.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", nullable = false, length = 50)
	@NotEmpty(message = "O nome não deve ser vazio")
	@NotNull(message = "O nome não deve ser nulo")
	@Pattern(regexp = "^(?:[\\\\p{Lu}&&[\\\\p{IsLatin}]])(?:(?:')?(?:[\\\\p{Ll}&&[\\\\p{IsLatin}]]))+(?:\\\\-(?:[\\\\p{Lu}&&[\\\\p{IsLatin}]])(?:(?:')?(?:[\\\\p{Ll}&&[\\\\p{IsLatin}]]))+)*(?: (?:(?:e|y|de(?:(?: la| las| lo| los))?|do|dos|da|das|del|van|von|bin|le) )?(?:(?:(?:d'|D'|O'|Mc|Mac|al\\\\-))?(?:[\\\\p{Lu}&&[\\\\p{IsLatin}]])(?:(?:')?(?:[\\\\p{Ll}&&[\\\\p{IsLatin}]]))+|(?:[\\\\p{Lu}&&[\\\\p{IsLatin}]])(?:(?:')?(?:[\\\\p{Ll}&&[\\\\p{IsLatin}]]))+(?:\\\\-(?:[\\\\p{Lu}&&[\\\\p{IsLatin}]])(?:(?:')?(?:[\\\\p{Ll}&&[\\\\p{IsLatin}]]))+)*))+(?: (?:Jr\\\\.|II|III|IV))?$", message = "Nome inválido")
	private String nome;

	@Column(name = "email", length = 50, unique = true)
	@NotEmpty(message = "O email não deve ser vazio")
	@NotNull(message = "O email não deve ser nulo")
	@Email(regexp = ".+[@].+[\\.].+", message = "Email inválido")
	private String email;

	@Column(name = "senha", nullable = false, length = 32)
	@NotEmpty(message = "A senha não deve ser vazia")
	@NotNull(message = "A senha não deve ser nula")
	@Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
	@Pattern(regexp = ".*[a-z].*", message = "A senha deve conter pelo menos uma letra minúscula")
	@Pattern(regexp = ".*[A-Z].*", message = "A senha deve conter pelo menos uma letra maiúscula")
	@Pattern(regexp = ".*\\d.*", message = "A senha deve conter pelo menos um dígito")
	@Pattern(regexp = ".*[@#$%^&+=.!].*", message = "A senha deve conter pelo menos um caractere especial")
	private String senha;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Postagem> postagens;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "amigos", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "amigo_id"))
	private List<Usuario> amigos;

	public Usuario(String nome, String email, String password, List<Usuario> amigos) {
		this.nome = nome;
		this.email = email;
		this.senha = password;
		this.amigos = amigos;
	}

	public Usuario() {

	}

}