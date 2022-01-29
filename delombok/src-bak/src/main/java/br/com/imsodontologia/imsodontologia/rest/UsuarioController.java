package br.com.imsodontologia.imsodontologia.rest;

import br.com.imsodontologia.imsodontologia.Resource.UsuarioService;
import br.com.imsodontologia.imsodontologia.model.Usuario;
import br.com.imsodontologia.imsodontologia.exception.UsuarioCadastradoException;
import br.com.imsodontologia.imsodontologia.exception.UsuarioFindByIdException;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {


    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody @Valid Usuario usuario){
        Usuario teste = usuarioService.salvar(usuario);
        if( teste == null){
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já cadastrado(a)!");
        }else {
            throw new ResponseStatusException(HttpStatus.CREATED);
        }
    }

    @GetMapping
    public List<Usuario> getAll(){
        return this.usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Usuario> getOne(@PathVariable Integer id){
        try {
             return this.usuarioService.findById(id);
        }catch (UsuarioFindByIdException e){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

}
