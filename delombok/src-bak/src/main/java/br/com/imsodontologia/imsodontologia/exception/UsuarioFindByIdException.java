package br.com.imsodontologia.imsodontologia.exception;

public class UsuarioFindByIdException extends RuntimeException
    {
        public UsuarioFindByIdException(){
            super ("Usuário não encontrado");

    }

}
