package br.com.imsodontologia.imsodontologia.exception;

public class UsuarioCadastradoException extends RuntimeException{

    public UsuarioCadastradoException(String username){
        super ("Usuário já cadastrado" + username);
    }
}
