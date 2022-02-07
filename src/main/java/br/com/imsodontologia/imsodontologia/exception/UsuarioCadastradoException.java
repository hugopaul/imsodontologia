package br.com.imsodontologia.imsodontologia.exception;

import org.springframework.dao.DataIntegrityViolationException;

import java.sql.SQLIntegrityConstraintViolationException;

public class UsuarioCadastradoException extends DataIntegrityViolationException {

    public UsuarioCadastradoException(String username){
        super ("Usuário já cadastrado" + username);
    }
}
