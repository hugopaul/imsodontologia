package br.com.imsodontologia.imsodontologia.controller;

import br.com.imsodontologia.imsodontologia.Resource.UsuarioService;
import br.com.imsodontologia.imsodontologia.exception.UsuarioCadastradoException;
import br.com.imsodontologia.imsodontologia.model.Paciente;
import br.com.imsodontologia.imsodontologia.model.Usuario;
import br.com.imsodontologia.imsodontologia.exception.UsuarioFindByIdException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Usuario salvar(@RequestBody @Valid Usuario usuario){
        try {
            return this.usuarioService.salvar(usuario);
        }catch (DataIntegrityViolationException e){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já Cadastrado");
        }
    }

    @GetMapping
    public List<Usuario> getAll(){
        return this.usuarioService.findAll();
    }

    @GetMapping("/perfil/{usuario}")
    public String getOne(@PathVariable String usuario){
        if(this.usuarioService.findPerfilByUsusario(usuario) == null) {
            return null;
        }
        return this.usuarioService.findPerfilByUsusario(usuario);
    }

    @GetMapping("/nome")
    @ResponseStatus(HttpStatus.OK)
    public String getNomePorUsername(@RequestParam String user){
        if(this.usuarioService.getNomePorUsername(user) == null) {
            return null;
        }
        return this.usuarioService.getNomePorUsername(user);
    }

    @PostMapping("/buscar-string")
    @ResponseStatus(HttpStatus.OK)
    public List<Usuario> getDoutorByNome(@RequestBody String getByNome){
        return this.usuarioService.buscarDocByNome(getByNome);
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
