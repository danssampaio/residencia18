package com.dansoft.redesocial.controller.Form;

import com.dansoft.redesocial.model.UsuarioLoginRole;

public record RegisterForm(String login, String email, String senha, UsuarioLoginRole role) {

}
